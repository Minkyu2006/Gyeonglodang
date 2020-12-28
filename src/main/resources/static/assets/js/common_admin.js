$(function() {
    // // 온도
    // $('.temp-progress').each(function() {
    //     var $this = $(this),
    //         _temp = $this.data('temp');
    //     console.log(_temp);
    //     if (_temp > 26) {
    //         $(this).parents('.temp').addClass('red');
    //         $(this).parents('.temp').removeClass('blue');
    //     } else if (_temp < 23) {
    //         $(this).parents('.temp').addClass('blue');
    //         $(this).parents('.temp').removeClass('red');
    //     } else {
    //         $(this).parents('.temp').removeClass('blue');
    //         $(this).parents('.temp').removeClass('red');
    //     }
    //     $this.circleProgress({
    //         max: 48,
    //         value: _temp,
    //         animationDuration: 400,
    //         textFormat: (val) => val + '°C'
    //     });
    // })
    // // 습도
    // $('.humidity-progress').each(function() {
    //     var $this = $(this),
    //         _percent = $this.data('percent');
    //     console.log(_percent);
    //     if (_percent > 70) {
    //         $(this).parents('.humidity').addClass('red');
    //         $(this).parents('.humidity').removeClass('blue');
    //     } else if (_percent < 40) {
    //         $(this).parents('.humidity').addClass('blue');
    //         $(this).parents('.humidity').removeClass('red');
    //     } else {
    //         $(this).parents('.humidity').removeClass('blue');
    //         $(this).parents('.humidity').removeClass('red');
    //     }
    //     $this.circleProgress({
    //         max: 100,
    //         value: _percent,
    //         animationDuration: 400,
    //         textFormat: (val) => val + '%'
    //     });
    // });
    // // button toggle
    // $('.console-button').on('click', function() {
    //     $(this).toggleClass('active');
    //     if ($(this).hasClass('light active')) {
    //         $(this).children('.fa-lightbulb').removeClass('fas');
    //         $(this).children('.fa-lightbulb').addClass('far');
    //     }else{
    //         $(this).children('.fa-lightbulb').removeClass('far');
    //         $(this).children('.fa-lightbulb').addClass('fas');
    //     }
    // })
    // 경고메시지 예제 추후 삭제
    $('.ex-button').on('click', function(e) {
        $('.alert').addClass('active');
        e.preventDefault();
    });
    $('.alert-pop__close').on('click', function(e) {
        $('.alert').removeClass('active');
        e.preventDefault();
    })
})
