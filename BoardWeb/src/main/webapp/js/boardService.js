/**
 *  boardService.js
 * 	목록 ,등록, 삭제 , 기능을 정의
 */
const svc = {
	// 목록
	replyList({bno,page}, loadCallback){
		const xhtp = new XMLHttpRequest();
		xhtp.open('get',`replyList.do?bno=${bno}&page=${page}`);
		xhtp.send();
		// 여기서 loadCallback 매개값 매서드의 this는 xhtp이다.
		xhtp.onload = loadCallback 
	}, // end of replyList
	// 등록
	addRply(param = {bno, replyer, content}, loadCallback){
		const xhtp = new XMLHttpRequest();
		xhtp.open('get',`addReply.do?bno=${param.bno}&content=${param.content}&replyer=${param.replyer}`);
		xhtp.send();
		xhtp.onload = loadCallback 
	},
	// 삭제
	removeRply(rno = 1, loadCallback){
		const xhtp = new XMLHttpRequest();
		xhtp.open('get',`removeReply.do?rno=${rno}`);
		xhtp.send();
		xhtp.onload = loadCallback 
	}
	
} // end of svc
// 댓글 등록 버튼에 클릭 이벤트 등록
