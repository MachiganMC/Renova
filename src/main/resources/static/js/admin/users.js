const addEl = {
    username: $("#add-user-username"),
    password: $("#add-user-password"),
    button: $("#add-user-submit"),
    alert: $("#add-user-alert")
};

function addUser() {
    if (!addEl.username.val())
        return showAlertEl(addEl.alert, "Veuillez définir un nom d'utilisateur.");
    if (!addEl.password.val())
        return showAlertEl(addEl.alert, "Veuillez définir le mot de passe par défaut.");
    beforeRequest(addEl.button, addEl.alert);
    sendRequest({
        url: 'users',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            username: addEl.username.val(),
            password: addEl.password.val()
        }),
        complete: () => addEl.button.removeAttr("disabled"),
        error: error => showAlertEl(addEl.alert, errorStatusToMessage(error.status)),
        success: () => location.reload()
    });
}

function errorStatusToMessage(status) {
    if (status === 409)
        return "Ce nom d'utilisateur est déjà utilisé.";
    return "Une erreur s'est produite, veuillez réessayer plus tard."
}

function disable(userId) {
    const section = $(`#user-section-${userId}`);
    const el = {
        button: section.find("button"),
        alert: section.find(".alert")
    };
    beforeRequest(el.button, el.alert);
    sendRequest({
        url: `users/${userId}`,
        type: 'DELETE',
        complete: () => el.button.removeAttr("disabled"),
        error: error => showAlertEl(el.alert, errorStatusToMessage(error.status)),
        success: () => section.remove()
    });
}
