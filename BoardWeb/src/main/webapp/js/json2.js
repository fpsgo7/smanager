document.querySelector('div.container-fluid>div:nth-of-type(2)').remove();
document.querySelector('div.container-fluid>table').remove();
// Asynchronous(비동기) Javascript And Xml (AJAX)
let employees;
let xhtp = new XMLHttpRequest();
xhtp.open('get',// 요청 방식
	'data/MOCK_DATA.json'// 파일명과 경로
);// 특정 페이지 요청
xhtp.send();
xhtp.onload = function(e){
	let jsonTest = xhtp.response;
	employees = JSON.parse(jsonTest);
	console.log(employees);
	initList();
}
document.querySelector('#searchGender').addEventListener('change',function(e){
		initList();
});
function initList(){
	let target = document.getElementById('empList');
	target.innerHTML = '';
	let selValue = document.querySelector('#searchGender').value;
	employees.forEach(emp => {
		if(selValue == 'All' || emp.gender == selValue){
			target.appendChild(makeRow(emp));	
		}
	});
}
// 사원 정보 => row 생성
function makeRow(emp = {}){
	let fields = ['id', 'first_name', 'last_name', 'salary'];
	let tr = document.createElement('tr');
	fields.forEach(field =>{
		let td = document.createElement('td');
		td.innerHTML = emp[field];
		tr.appendChild(td);
	})
	return tr;
}