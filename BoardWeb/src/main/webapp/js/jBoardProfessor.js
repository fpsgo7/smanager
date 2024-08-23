/**
 * jBoard.js
 */
console.log('jBoard.js start');
let page = 1;

// 등록이벤트.
$('#addReply').on('click', function(e) {
	svc.addReply({ bno, replyer, content: $('#content').val() },
		function(result) {
			if (result.retCode == 'Success') {
				//let list = makeRow(result.retVal);
				//$('#replyList').prepend(list);
				page = 1;
				showList();
				$('#content').val('');
			} else {
				alert('error!!')
			}
		},
		function(err) {
			console.error(err);
		})
})

// 링크이벤트.
$('ul.pagination').on('click', 'a', function(e) {
	e.preventDefault();
	page = $(this).data('page');
	showList();
})

// 댓글삭제 Ajax함수.
function removeAjax(e) {
	let list = $(this).closest('li');
	let rno = list.data('rno');
	svc.removeReply(rno,
		function(result) {
			if (result.retCode == 'Success')
				//list.remove();
				showList();
			else
				alert('error!!')
		},
		function(err) {
			console.error(err);
		})

}

// 목록출력.
function showList() {
	svc.replyList({ bno, page },
		function(result) {
			$('#replyList li:gt(0)').remove(); // 화면에 출력된 목록을 지우기.
			$.each(result, function(idx, elem) {
				let list = makeRow(elem);
				$('#replyList').append(list);
			})
			// 페이징 생성.
			makePaination();
		}, function(err) {
			console.error(err);
		})
};
showList();

// row 생성함수.
function makeRow(elem) {
	let list = $('#replyList>li').eq(0).clone();
	let btn = $('<button>삭제</button>').on('click', removeAjax);
	list.css('display', 'block');
	list.data('rno', elem.replyNo);
	list.find('span').eq(0).text(elem.replyNo)
		.end()
		.eq(1).text(elem.replyContent)
		.end()
		.eq(2).text(elem.replyer)
		.end()
		.eq(3).html(btn);

	return list;
}

// 페이지 생성.
let pagination = $('ul.pagination');
function makePaination() {
	svc.pagingCount(bno,
		function(result) {
			// 조회된 건수를 기준으로 페이지를 생성해주기.
			let totalCnt = result.totalCount;
			let startPage, endPage; // 현재페이지를 기준으로 계산한 첫페이지번호 ~ 마지막페이지번호.
			let next, prev; // 이전, 이후 체크하는 변수.
			let realEnd = Math.ceil(totalCnt / 5); // 20page

			endPage = Math.ceil(page / 5) * 5;
			startPage = endPage - 4;
			endPage = endPage > realEnd ? realEnd : endPage;

			prev = startPage > 1; // 이전 10개의 페이지의 존재를 구분.
			next = endPage < realEnd ? true : false;

			pagination.html(''); // 페이징 목록을 지우고 새로 그려주기 위해서.
			let list = $('<li class="page-item" />');
			if (!prev) {
				list.append($('<span class="page-link">Previous</span>')).addClass('disabled');
			} else {
				list.append($('<a class="page-link">Previous</a>')
					.attr('href', '#').data('page', (startPage - 1)));
			}
			list.appendTo(pagination);

			for (let p = startPage; p <= endPage; p++) {
				list = $('<li class="page-item" />');
				if (p == page) {
					list.addClass('active').attr('aria-current', 'page');
					list.append($('<span class="page-link" />').text(p));
				} else {
					list.append($('<a class="page-link" />')
						.attr('href', p).text(p).data('page', p));
				}
				list.appendTo(pagination);
			}

			list = $('<li class="page-item" />');
			if (!next) {
				list.append($('<span class="page-link">Next</span>')).addClass('disabled');
			} else {
				list.append($('<a class="page-link">Next</a>')
					.attr('href', '#').data('page', endPage + 1));
			}
			list.appendTo(pagination);

		},
		function(err) {
			console.error(err);
		})
}