const el = {
    add: {
        name: $("#current-promo-name"),
        description: $("#current-promo-description"),
        end: $("#current-promo-end-date"),
        alert: $("#alert-current-promo"),
        button: $("#button-current-promo")
    },
    alert: $("#global-alert")
};

$(window).on('load', () => {
    let today = new Date(new Date().getTime() + 3600 * 1000 * 24);
    let dd = today.getDate();
    let mm = today.getMonth() + 1;
    const yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    el.add.end.attr('min', yyyy + '-' + mm + '-' + dd);
});

function createPromo() {
    if (isPromoInvalid(el.add, el.add.alert)) return;
    beforeRequest(el.add.button, el.add.alert);
    sendRequest({
        url: '/admin/promos',
        type: 'POST',
        data: {
            name: el.add.name.val(),
            description: el.add.description.val(),
            endDate: el.add.end.val()
        },
        complete: () => el.add.button.removeAttr("disabled"),
        success: () => location.reload(),
        error: () => showAlertEl(el.add.alert, "Une erreur s'est produite, veuillez réessayer plus tard.")
    });
}

function isPromoInvalid(el, alertEl) {
    if (!el.name.val())
        return showAlertEl(alertEl, "Une promotion doit obligatoirement avoir un nom.") || true;
    return false;
}

function removePromo(id, button) {
    button = $(button);
    beforeRequest(button, el.alert);
    sendRequest({
        url: `/admin/promos/${id}`,
        type: 'DELETE',
        complete: () => button.removeAttr("disabled"),
        success: () => location.reload(),
        error: () => showAlertEl(el.alert, "Une erreur s'st produite, veuillez réessayer plus tard.")
    });
}
