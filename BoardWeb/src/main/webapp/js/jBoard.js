console.log('jBoard.js start');
let page = 1;

// 댓글 리스트 그리는 함수 만들기
showReply();
function showReply(){
	svc.replyList({bno,page},showReplySuccess,showError);
	svc.pagingCount(bno,pagingCountSuccess,showError);
}

function showReplySuccess(data){
	// 기존 목록 삭제
	$('#replyList li').each((idx,item) => {
		if(idx != 0){
			item.remove();
		}
	})
	
	//console.log(data);// 알아서 배열형태로 반환해준다.
	$(data).each((idx,reply) => {
		// console.log(reply);
		$('#replyList').append(makeRow(reply));
	})
	
}

function makeRow(reply = {}){
	let cloned = $('div.reply>div.content li:eq(0)').clone();
	$(cloned).css('display','block').attr('data-rno',reply.replyNo);
	cloned.find('span:eq(0)').text(reply.replyNo);
	cloned.find('span:eq(1)').text(reply.replyContent);
	cloned.find('span:eq(2)').text(reply.replyer);
	cloned.find('button').on('click',removeReply)
	return cloned;
}

// 댓글 추가하기
$('#addReply').on('click',function(){
	let content = $('#content').val();
	let param = {bno, replyer, content}

	// 댓글 권환과 값확인
	if(!replyer){
		alert("권한이 없습니다.");
		return;
	}
	if(!content){
		alert("값을입력해주세요");
		return;
	}

	svc.addReply(param,addReplySuccess,showError)
})

function addReplySuccess(data){
	console.log(data);
	if(data.retCode == "Success"){
		page = 1; 
		showReply();
	}else{
		alert("댓글 추가가 실패하였습니다.")
	}
}

function removeReply(){
	let li = $(this).parent().parent();
	let rno = li.data('rno');
	let replyReplyer = li.find('span:eq(2)').text();

	if(replyReplyer != replyer){
		alert("권한이 없습니다.");
		return;
	}

	svc.removeReply(rno, removeReplySuccess, showError);
}

function removeReplySuccess(data){
	if(data.retCode == "Success"){
		showReply();
	}else{
		alert("삭제 실패");
	}
}

function pagingCountSuccess(data){
	let pagination = $('ul.pagination');
	console.log(data);
	let totalCnt = data.totalCount;
	let startPage, endPage;
	let next, prev;
	let realEnd = Math.ceil(totalCnt/5);

	endPage = Math.ceil(page/10) * 10;
	startPage = endPage - 9;
	endPage = endPage > realEnd ? realEnd : endPage;

	prev = startPage > 1;
	next = endPage < realEnd ? true : false;

	// 기존 html 제거하기
	pagination.html('');

	// 이전 페이지 여부 
	let list = $('<li class="page-item"/>');
	if(!prev){
		list.append($('<span class="page-link">prev</span>')).addClass('disabled');
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
}

// 페이지 링크 이벤트
$('ul.pagination').on('click','a',function(e){
	e.preventDefault();
	page = $(this).data('page');
	showReply();
})

// 공통 오류 처리
function showError(){
	alert("통신 오류가 발생하였습니다.");
}