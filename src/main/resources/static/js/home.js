if (window.screen.width > 768) {
    $("header.phone-navbar-container").remove();
} else {
    $("header.computer-navbar-container").remove();
    const offCanvas = new bootstrap.Offcanvas(document.getElementById('phone-navbar'));
    document.addEventListener("DOMContentLoaded", function(){
        document.getElementById("open-navbar").addEventListener('click',function (e){
            e.preventDefault();
            e.stopPropagation();
            offCanvas.toggle();
        });
    });

    function scrollToFromOffCanvas(el) {
        offCanvas.toggle();
        el[0].scrollIntoView();
    }
}

const banner = $(".hide-on-scroll");
let lastScroll = 0;
$(window).scroll(function () {
    const scroll = $(this).scrollTop();
    if (scroll < lastScroll) {
        banner.css("opacity", "1");
    } else {
        banner.css("opacity", "0");
    }
    lastScroll = scroll;
});

const promosCountdowns = [];
$("[data-time-left]").each(function () {
    const el =$(this);
    promosCountdowns.push({
        time: el.attr('data-time-left'),
        countdownEl: el
    });
});

setInterval(function () {
    const now = new Date().getTime();
    for (let promo of promosCountdowns) {
        const timeLeft = promo.time - now;
        if (timeLeft <= 0) {
            promo.countdownEl.text("Promo finie ...")
            promosCountdowns.splice(promosCountdowns.indexOf(promo), 1);
        } else {
            const days = Math.floor(timeLeft / (1000 * 60 * 60 * 24));
            const hours = Math.floor((timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            const minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);
            promo.countdownEl.text(`${days >= 1 ? days + ' jours ' : ''} ${hours} h ${minutes} min ${seconds} s`);
        }
    }
});

function showPromoDescription(parentEl) {
    parentEl.find('button').toggle();
    parentEl.find('div').toggle();
}
