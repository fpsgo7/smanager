const friends = [
	{name:'홍길동',
	address: '대구 100번지',
	height: 170},
	{name:'김민규',
	address: '대구 200번지',
	height: 175},
	{name:'이성윤',
	address: '대전 100번지',
	height: 180}
]

makeList();

function makeList(){
	friends.forEach(function(friend){
		let tr = makeTr(friend);
		document.getElementById('list').appendChild(tr);
	})
}

// 등록 버튼에 클릭 이벤트 추가
document.getElementById('addBtn').addEventListener('click', addBtnFunc);
function addBtnFunc(e) {
	let name = document.getElementById('fname').value;
	let address = document.getElementById('faddress').value;
	let height = document.getElementById('height').value;
	if(!name || !address || !height){
		alert('값을 입력하세요');
		return;
	}
	let friend = {name,address,height};
	
	let tr = makeTr(friend);
	document.getElementById('list').appendChild(tr);

	alert('OK');
	fname.value= '';
	faddress.value = '';
	document.getElementById('height').value = '';
}
// friend => tr 생성해주는 기능
function makeTr(friend = {name:'Hong',address:'Seoul',height:170}){
	
	// tr 만드는 부분
	let tr = document.createElement('tr');
	
	tr.addEventListener('click',detailCallback, false);
	
	for(let prop in friend){
		let td = document.createElement('td');
		td.innerHTML = friend[prop];
		tr.appendChild(td);
	}
	// 삭제버튼
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerHTML = "삭제";
	// 버튼에 클래스 부여하기
	btn.setAttribute('class','btn btn-danger');
	btn.addEventListener('click', function(e){
		console.log(e);
		e.stopPropagation();// 이벤트가 전파되는 것을 차단한다.
		e.target.parentElement.parentElement.remove();
	},false);
	td.appendChild(btn);
	tr.appendChild(td);
	// 채크박스 추가하기
	td = document.createElement('td');
	let checkBox =  document.createElement('input');
	checkBox.setAttribute("type","checkbox");
	checkBox.addEventListener("change",changeEtcFnc);
	td.appendChild(checkBox);
	tr.appendChild(td);
	// tr 클릭 이벤트 추가

	return tr;
}
// 수정 버튼에 클릭 이벤트 추가
document.getElementById('modBtn').addEventListener('click', modBtnFnc);
// modBtnFnc 이벤트 핸들러
function modBtnFnc(e){
	// 이름으로 찾자
	let name = document.getElementById('fname').value;
	let address = document.getElementById('faddress').value;
	let height = document.getElementById('height').value;
	document.querySelectorAll("#list tr").forEach(function(e){
		if(name == e.children[0].innerHTML){
			console.log(e.children[0].innerHTML + "이 수정되었습니다.");
			e.children[1].innerHTML = address;
			e.children[2].innerHTML = height;
		}
	});
}
// 선택 삭제 버튼에 클릭 이벤트 추가
document.getElementById('delBtn').addEventListener('click', delBtnFnc);
function delBtnFnc(){
	// 체크된 박스 대상을 전부 가지고 온다.
	let checkedBoxes = document.querySelectorAll('div.container-fluid>table>tbody'+
	' input[type="checkbox"]:checked');
	checkedBoxes.forEach(function (item){
		// 체크박스의 부모의 부모인 tr을 찾는다.
		let tr = item.parentElement.parentElement;
		tr.remove();
	});
	
}
// tr 대상의 상세정보 가져오기
function detailCallback(e){
	let tr = e.target.parentElement;// 이벤트를 대상으로 tr 영역을 찾아야함
	// this : 1) 함수영역 windwo
	// 2) 이벤트 대상
	// 3) 객체에서는 객체 
	// 체크박스를 선택하면 e 선택대상이 tr의 자식인 td가 선택되어
	// 조건문을 사용하였다.
	if(tr.tagName=="TD"){ // tagName 은 대문자로 온다.
		tr = tr.parentElement;// td의 부모인 tr을 찾자
	}
	fname.value= tr.children[0].innerHTML;
	faddress.value = tr.children[1].innerHTML;
	document.getElementById('height').value = tr.children[2].innerHTML;
}
// 하나 체크시 전체 체크하기 
document.querySelector('div.container-fluid >table>thead input[type="checkbox"]')
	.addEventListener('change', changeFnc);
function changeFnc(e){
	console.log(document.querySelectorAll('div.container-fluid>table>tbody'+
	' input[type="checkbox"]:checked').length);
	
	document.querySelectorAll('div.container-fluid>table>tbody'+
	' input[type="checkbox"]').forEach(function(item){
		item.checked = e.target.checked;
	})
}
// 전부 체크되거나 체크가 풀리면 전체 체크 수정하기
function changeEtcFnc(e){
	let checkedBoxCount = document.querySelectorAll('div.container-fluid>table>tbody'+
	' input[type="checkbox"]:checked').length;
	let checkBoxCount = document.querySelectorAll('div.container-fluid>table>tbody'+
	' input[type="checkbox"]').length;
	if(checkedBoxCount == checkBoxCount){
		document.querySelector('div.container-fluid >table>thead input[type="checkbox"]')
			.checked = true;
	}else{
		document.querySelector('div.container-fluid >table>thead input[type="checkbox"]')
			.checked = false;
	}
}