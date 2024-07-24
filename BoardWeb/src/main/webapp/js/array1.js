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
document.getElementById('addBtn').addEventListener('click',function(e){
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
});

// friend => tr 생성해주는 기능
function makeTr(friend = {name:'Hong',address:'Seoul',height:170}){
	function detailCallback(e){
		fname.value= friend.name;
		faddress.value = friend.address;
		document.getElementById('height').value = friend.height;
	}
	// tr 만드는 부분
	let tr = document.createElement('tr');
	
	tr.addEventListener('click',detailCallback);
	
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
		e.target.parentElement.parentElement.remove();
	});
	td.appendChild(btn);
	tr.appendChild(td);
	// 채크박스 추가하기
	td = document.createElement('td');
	let checkBox =  document.createElement('input');
	checkBox.setAttribute("type","checkbox");
	checkBox.setAttribute("class","checkbox");
	td.appendChild(checkBox);
	tr.appendChild(td);
	// tr 클릭 이벤트 추가

	return tr;
}

// 수정 버튼에 클릭 이벤트 추가
document.getElementById('modBtn').addEventListener('click',function(e){
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
});
// 아이디 allCheckBox 인 체크 박스클릭하면 다른 체크박스들 동작하기 
document.getElementById('allCheckBox').addEventListener('change',function(e){
	document.querySelectorAll(".checkbox").forEach(function(e){
		if(document.getElementById('allCheckBox').checked){ // allCheckBox 가 true 가 될 경우
			e.checked = true;// 다른 모든 체크박스도 true 로 바꾼다.
		}else{// allCheckBox 가 false 인경우
			e.checked = false; // 다른 모든 체크박스를 false 로 바꾼다.
		}
	})
});