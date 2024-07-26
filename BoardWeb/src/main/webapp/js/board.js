/**
 * board.js
 */
let page = 1;


document.querySelector('#addReply').addEventListener('click',function(){
	let content = document.querySelector('#content').value;
	let parm = {bno, replyer, content};
	
	if(!replyer){
		alert("권한이 없습니다.");
		return;
	}
	if(!content){
		alert("값을입력해주세요");
		return;
	}
	
	svc.addRply(parm, function(){
		// 등록완료할경우 => 화면에 등록된 화면 추가
		let result = JSON.parse(this.responseText);
		console.log(result);
		if(result.retCode == "Success"){
			// 새로 작성한글은 맨앞에 있기에 페이징 맨앞으로 이동해주며 새로 그려주자
			svc.replyList({bno,page},function(){
				// 기존 목록 삭제
				replyList.querySelectorAll('li').forEach((li,idx) => {
					if(idx != 0){
						li.remove();
					}
				})
				// 글목록 그리기
				let result = JSON.parse(this.responseText);
				result.forEach(reply =>{
					replyList.appendChild(makeRow(reply));
				});
			});
		}else{
			alert("추가 실패");
		}
	});
});
// 댓글 출력
svc.replyList({bno,page},function(){
	let result = JSON.parse(this.responseText);
	result.forEach(reply =>{
		replyList.appendChild(makeRow(reply));
	});
});

// reply => row 생성
function makeRow(reply = {}){
	let cloned = document
		.querySelector('div.reply>div.content li')
		.cloneNode(true); // li 의 하위 노드까지 카피하기위해 (true)를 작성한다.
	// 템플릿 용의 스타일을 수정하여 보이게한다.
	cloned.style.display = 'block';
	cloned.setAttribute('data-rno', reply.replyNo);
	cloned.querySelector('span:nth-of-type(1)')
		.innerText = reply.replyNo;
	cloned.querySelector('span:nth-of-type(2)')
		.innerText = reply.replyContent;
	cloned.querySelector('span:nth-of-type(3)')
		.innerText = reply.replyer;	
	cloned.querySelector('button')
		.addEventListener('click',deleteReplyFnc);
	return cloned;
}
function deleteReplyFnc(e){
	let li = e.target.parentElement.parentElement;
	let rno = li.dataset.rno;
	let replyReplyer = li.querySelector('span:nth-of-type(3)').innerText;
	
	if( replyer != replyReplyer){
		alert("권한이 없습니다.");
		return;
	}
	
	svc.removeRply(rno,function(e){
		let result = JSON.parse(this.responseText);
		if(result.retCode == "Success"){
			// 향후 페이징 기능이 완성되면 수정할것!
			document.querySelector(`li[data-rno="${rno}"]`).remove();
		}else{
			alert("삭제 실패");
		}
	})
}

// 페이징 영역의 a 태그를 클릭하면
document.querySelectorAll('div.reply ul.pagination a')
	.forEach(item => {
		item.addEventListener('click', function(e){
			page = item.innerHTML;
			svc.replyList({bno,page},function(){
				// 기존 목록 삭제
				replyList.querySelectorAll('li').forEach((li,idx) => {
					if(idx != 0){
						li.remove();
					}
				})
				
				// 글목록 그리기
				let result = JSON.parse(this.responseText);
				result.forEach(reply =>{
					replyList.appendChild(makeRow(reply));
				});
			});
		})
	});
