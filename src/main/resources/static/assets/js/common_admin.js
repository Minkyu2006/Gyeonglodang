$(function() {

	// // battery progress
	// $('.battery__progress').each(function() {
	// 	var $this = $(this),
	// 		_percent = $this.data('percent');
	//
	// 	if (31 > _percent) {
	// 		$this.parents('.battery').addClass('empty');
	// 	} else {
	// 		$this.parents('.battery').removeClass('empty');
	// 	}
	//
	// 	$('.battery__bar').width(_percent + "%");
	// 	$('.battery-percent').text(_percent + "%")
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
