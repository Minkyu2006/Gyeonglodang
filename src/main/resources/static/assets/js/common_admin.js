$(function() {

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
