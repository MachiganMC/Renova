function changeOnClickFunction(button, newFunction) {
    button.removeAttr("onclick");
    button.off("click");
    button.click(newFunction);
}

function scrollToId(id) {
    document.getElementById(id).scrollIntoView({
        behavior: "smooth",
        block: "nearest",
        inline: "center"
    });
}
