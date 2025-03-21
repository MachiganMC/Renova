const slideShowEl = {
    editInput: $("#edit-current-picture"),
    editImg: $("#current-picture"),
    alert: $("#alert-current-picture"),
    button: $("#button-current-picture")
};

const maxFileSizeEl = $("#max-file-size");
const maxFileSize = {
    value: maxFileSizeEl.attr("data-value"),
    toString: maxFileSizeEl.attr("data-string")
};

function previewPicture(input) {
    if (!input.files || !input.files[0]) return;
    const reader = new FileReader();
    reader.onload = e => {
        slideShowEl.editImg.attr("src", e.target.result);
        slideShowEl.editImg.css("display", "inline-block");
    };
    reader.readAsDataURL(input.files[0]);
}

function savePicture() {
    const input = slideShowEl.editInput[0];
    if (!input.files || !input.files[0])
        return showAlert(slideShowEl.alert, "Veuillez sélectionner une image");
    const file = input.files[0];
    if (file.size > maxFileSize.value)
        return showAlertEl(slideShowEl.alert, `La taille de l'image ne peut pas dépasser ${maxFileSize.toString} (taille: ${(file.size / 1_000_000).toFixed(2)}MB).`);
    beforeRequest(slideShowEl.button, slideShowEl.alert);
    const formData = new FormData();
    formData.append("picture", input.files[0]);
    sendRequest({
        url: '',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        complete: () => slideShowEl.button.removeAttr("disabled"),
        success: () => location.reload(),
        error: error => showAlertEl(slideShowEl.alert, errorStatusToMessage(error.status))
    });
}

function errorStatusToMessage(error) {
    if (error === 415)
        return "Veuillez mettre une image.";
    return "Une erreur s'est produite, veuillez réessayer plus tard.";
}
