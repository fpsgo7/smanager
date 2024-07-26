/**
 * board.js
 */
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
			// replyList id를 가진 대상에 자식으로 추가한다.
			replyList.appendChild(makeRow(result.retVal));
		}else{
			alert("추가 실패");
		}
	});
});

svc.replyList(bno,drawReplyLi);
// ul li 그리기
function drawReplyLi(){
	let result = JSON.parse(this.responseText);
	result.forEach(reply =>{
		replyList.appendChild(makeRow(reply));
	});
}

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
	
	if( replyer != replyer){
		alert("권한이 없습니다.");
		return;
	}
	
	svc.removeRply(rno,function(e){
		let result = JSON.parse(this.responseText);
		if(result.retCode == "Success"){
			document.querySelector(`li[data-rno="${rno}"]`).remove();
		}else{
			alert("삭제 실패");
		}
	})
}