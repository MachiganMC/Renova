const el = {
    username: $("#user-username"),
    password: $("#user-password"),
    buttons: $("#user-username-button").add($("#user-password-button")),
    alert: $(".alert"),
    toast: $("#toast")
};

function errorStatusToMessage(status) {
    if (status === 409)
        return "Ce nom d'utilisateur est déjà utilisé.";
    return "Une erreur s'est produite, veuillez réessayer plus tard.";
}

function update() {
    if (!el.username.val())
        return showAlertEl(el.alert, "Veuillez définir le nouveau nom d'utilisateur");
    beforeRequest(el.buttons, el.alert);
    sendRequest({
        url: '',
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify({
            username: el.username.val(),
            password: el.password.val()
        }),
        complete: () => el.buttons.removeAttr("disabled"),
        error: error => showAlertEl(el.alert, errorStatusToMessage(error.status)),
        success: () => logout()
    });
}
