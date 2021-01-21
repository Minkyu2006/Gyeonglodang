$(function() {
    // // 온도
    // const temp = new CircleProgress('.temp .progress', {
    //     max: 48,
    //     value: 28,
    //     animationDuration: 400,
    //     textFormat: (val) => val + '°C'
    // });
    //
    // if (temp.value > 26) {
    //     $('.temp').addClass('red');
    //     $('.temp').removeClass('blue');
    // } else if (temp.value < 23) {
    //     $('.temp').addClass('blue');
    //     $('.temp').removeClass('red');
    // } else {
    //     $('.temp').removeClass('blue');
    //     $('.temp').removeClass('red');
    // }

    // // 습도
    // const humidity = new CircleProgress('.humidity .progress', {
    //     max: 100,
    //     value: 90,
    //     animationDuration: 400,
    //     textFormat: (val) => val + '%'
    // });
    //
    // if (humidity.value > 70) {
    //     $('.humidity').addClass('blue');
    //     $('.humidity').removeClass('red');
    // } else if (humidity.value < 40) {
    //     $('.humidity').addClass('red');
    //     $('.humidity').removeClass('blue');
    // } else {
    //     $('.humidity').removeClass('blue');
    //     $('.humidity').removeClass('red');
    // }
	
	// 공기질
    // const airqual = new CircleProgress('.air .progress', {
    //     max: 600,
    //     value: 90,
    //     animationDuration: 400,
    //     textFormat: (val) => val + '㎍/㎥'
    // });
    //
    // if (airqual.value > 80) {
    //     $('.air').addClass('red');
    //     $('.air').removeClass('blue');
    //     $('.air__state').text('나쁨');
    // }  else if (airqual.value > 30) {
    // 	$('.air').removeClass('blue');
    //     $('.air').removeClass('red');
    //     $('.air__state').text('보통');
    // } else {
    //     $('.air').addClass('blue');
    //     $('.air').removeClass('red');
    //     $('.air__state').text('좋음');
    // }

    // 경고메시지 예제 추후 삭제
    //$('.ex-button').on('click', function(e) {
    //    $('.alert').addClass('active');

    //    e.preventDefault();
    //});
    $('.alert-pop__close').on('click', function(e) {
        $('.alert').removeClass('active');

        e.preventDefault();
    });
    
    $('.sensor-card').on('click', function(e) {
    	if ($(this).hasClass('sensor-card--temp')) {
    		$('.sensor-pop.sensor-pop--temp').addClass('active');
    	} else if ($(this).hasClass('sensor-card--humidity')) {
    		$('.sensor-pop.sensor-pop--humidity').addClass('active');
    	} else if ($(this).hasClass('sensor-card--air')) {
    		$('.sensor-pop.sensor-pop--air').addClass('active');
    	}
    	
    	

        e.preventDefault();
    });
    
    $('.sensor-pop__close').on('click', function(e) {
        $('.sensor-pop').removeClass('active');

        e.preventDefault();
    })
    
    $('.exit-btn--cancel').on('click', function(e) {
    	$(this).parents('.alert').removeClass('active');
    })
    
    

})
