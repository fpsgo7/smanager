document.querySelector("#show").remove();
document.querySelector('div.container-fluid>div:nth-of-type(2)').remove();
document.querySelector('div.container-fluid>table').remove();
// Asynchronous(비동기) Javascript And Xml (AJAX)
let studentList;
let xhtp = new XMLHttpRequest();
xhtp.open('get',// 요청 방식
	'studentJson.do'// 파일명과 경로
);// 특정 페이지 요청
xhtp.send();
xhtp.onload = function(e){
	let jsonTest = xhtp.response;
	studentList = JSON.parse(jsonTest);
	initList();
}
function initList(){
	let target = document.getElementById('studentList');
	target.innerHTML = '';
	studentList.forEach(emp => {
		target.appendChild(makeRow(emp));	
	});
}
// 사원 정보 => row 생성
function makeRow(emp = {}){
	let fields = ['stdNo', 'stdName','stdPhone'];
	let tr = document.createElement('tr');
	tr.setAttribute('data-sno', emp.stdNo);
	fields.forEach(field =>{
		let td = document.createElement('td');
		td.innerHTML = emp[field];
		tr.appendChild(td);
		
	})
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.setAttribute("class" , "btn btn-danger");
	btn.innerHTML = "삭제";
	btn.addEventListener("click",deleteRowFnc);
	td.appendChild(btn);
	tr.appendChild(td);
	return tr;
}
function deleteRowFnc(e){
	let tr = e.target.parentElement.parentElement;
	let sno = tr.firstChild.innerTEXT;
	// 다른 방법
	sno = tr.getAttribute("data-sno");
	sno = tr.dataset.sno;
	const delHttp = new XMLHttpRequest();
	delHttp.open(
		'get',
		'removeStudent.do?sno=' + sno
	);
	delHttp.send();
	delHttp.onload = function(){
		let result = JSON.parse(delHttp.responseText); // {"retCode": "Success"}
		if(result.retCode == "Success"){
			alert("성공");
			tr.remove();
		}else if(result.retCode == "Fail"){
			alert("실패");
		}
	}
}
// 추가 버튼에 이벤트 추가
document.querySelector("#addStudentBtn")
	.addEventListener('click',addRowFnc);
// 학생 추가 이벤트 메서드
function addRowFnc(){
	// 입력값 가져오기
	let sno = document.querySelector("#sno").value;
	let sname = document.querySelector("#sname").value;
	let phone = document.querySelector("#phone").value;

	let addHttp = new XMLHttpRequest();
	addHttp.open(
		'get',
		`addStudent.do?sno=${sno}&sname=${sname}&phone=${phone}`
	);
	addHttp.send();
	addHttp.onload = function(e){
		console.log(addHttp.response);
		let result = JSON.parse(addHttp.response); // {"retCode": "Success"}
		if(result.retCode == "Success"){
			console.log("tr 이 추가됩니다.");
			let tr = makeRow(result.retVal);
			document.getElementById('studentList').appendChild(tr);
		}
	}
}
