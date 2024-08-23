/**
 * 
 */
const svc = {
	// 목록.
	replyList(param = { bno, page }, successCall, errorCall) {
		$.ajax({
			url: 'replyList.do',
			data: { bno: param.bno, page: param.page },
			dataType: 'json',
			success: successCall,
			error: errorCall
		});
	},	// end of replyList
	// 등록.
	addReply(param = { bno, replyer, content }, successCall, errorCall) {
		$.ajax({
			url: 'addReply.do',
			data: { bno: param.bno, content: param.content, replyer: param.replyer },
			dataType: 'json',
			success: successCall,
			error: errorCall
		})
	},
	// 삭제.
	removeReply(rno = 1, successCall, errorCall) {
		$.ajax({
			url: 'removeReply.do',
			data: { rno: rno },
			dataType: 'json',
			success: successCall,
			error: errorCall
		})
	},
	// 댓글갯수.
	pagingCount(bno = 1, successCall, errorCall) {
		$.ajax({
			url: 'pagingCount.do',
			data: { bno: bno },
			dataType: 'json',
			success: successCall,
			error: errorCall
		})
	}
} // end of svc.
