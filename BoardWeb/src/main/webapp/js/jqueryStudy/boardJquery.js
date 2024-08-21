console.log('jquery start');
let page = 1;

showReplyList();
function showReplyList(){
	$.ajax({
		url: 'replyList.do',// 서버 url 호출
		data: {bno: bno, page: page} ,// 서버에 전달할 파라미터
		dataType: 'json', // 서버로부터 전달받은 content 타입
		success: function(result){
			$.each($('#replyList li'), function(i, ele){
				if(i !=0){
					ele.remove();
				}
			})
			// jquery foreach 문
			$.each(result,function(i,ele){
				// console.log("댓글",i,ele);
				let temp = $('#replyList li:eq(0)').clone();
				temp.attr('data-rno',ele.replyNo);//  데이터 담기위한 속성
				temp.css('display','block');
				temp.find('span:eq(0)').html(ele.replyNo);
				temp.find('span').eq(1).html(ele.replyContent);
				temp.find('span').eq(2).html(ele.replyer);
				// 버튼 생성을 새로한다.
				let btn = $('<button>삭제</button>').on('click', deleteRow);
				
				temp.find('span').eq(3).html(btn);
				$('#replyList').append(temp);
			})
		},
		error: function(err){
			console.log(err);
		}
	}); // 댓글리스트 AJAX 끝
}

function deleteRow(){
	// 삭제 ajax
	let li = $(this).parent().parent();
	let rno = li.data('rno');
	$.ajax({
		url: 'removeReply.do',
		data: {rno: rno},
		dataType: 'json',
		success: function(result){
			console.log(result);
			if(result.retCode == "Success"){
				showReplyList();
			}else{
				alert("처리중 예외 발생");
			}
		},
		error: function(err){
			console.log(err);
		}
	});
}

$('#addReply').on('click',addRow);

function addRow(){
	let content = $('#content').val();
	console.log(content);
	$.ajax({
		url: 'addReply.do',
		data: {
			replyer: replyer,
			content: content,
			bno: bno
		},
		dataType: 'json',
		success: function(result){
			if(result.retCode == "Success"){
				alert("댓글 추가 성공");
				showReplyList();
			}else{
				alert("처리중 예외 발생");
			}
		},
		error: function(err){
			console.log(err);
		}
	});
	
	$('#content').val('');
}