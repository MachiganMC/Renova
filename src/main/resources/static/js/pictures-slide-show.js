const el = {
    modal: $("#picture-slideshow"),
    img: $(".full-screen-picture"),
    arrowPrevious: $(".picture-slideshow-previous"),
    arrowNext: $(".picture-slideshow-next"),
    deleteButton: $("#picture-slideshow-delete")
};

$("#picture-slideshow .modal-body").click(e => {
    if (!$(e.target).hasClass('slide-show-arrow'))
        el.modal.modal("hide");
});

function showPicture(uuid) {
    el.img.attr("src", `/pictures/${uuid}`);
}

function showPictureModal(index) {
    changeCurrentPictureModal(index);
    el.modal.modal('show');
}

function nextPictureModal(currentUuid) {
    const index = pictures.indexOf(currentUuid);
    changeCurrentPictureModal(index === pictures.length - 1 ? 0 : index + 1);
}

function previousPictureModal(currentUuid) {
    const index = pictures.indexOf(currentUuid);
    changeCurrentPictureModal(index === 0 ? pictures.length - 1 : index - 1)
}

function changeCurrentPictureModal(index) {
    const newUuid = pictures[index];
    changeOnClickFunction(el.arrowNext, () => nextPictureModal(newUuid));
    changeOnClickFunction(el.arrowPrevious, () => previousPictureModal(newUuid));
    changeOnClickFunction(el.deleteButton, () => deletePicture(newUuid));
    showPicture(newUuid);
}

function deletePicture(uuid) {
    beforeRequest(el.deleteButton);
    sendRequest({
        url: `/admin/pictures/${uuid}`,
        type: 'DELETE',
        complete: () => location.reload()
    })
}
