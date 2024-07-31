let sido = "서울특별시"; // 도시값

// 접종 센터 정보
let centerAry = [];		
// 규칙 지정
let arrayFunc = result =>{
	centerAry = result.data;
	//console.log('2',centerAry);
	
	// forEach,map,filter,reduce.
	let newAry = centerAry.filter(function(center,idx,ary){
		return center.sido == sido;
	});
	console.log(newAry);
	document.querySelector('tbody').innerHTML = "";
	let field = ['id','centerName','address','phoneNumber'];
	newAry.forEach(center =>{
		let tr = document.createElement('tr');
		tr.addEventListener('clcick',function(e){
			//location.href = 'map.jsp?lat='+center.lat + '&lng='+center.lng;
			window.open("map.jsp?lat="+center.lat +"&lng="+center.lng);
		});
		for(let prop of field){
			let td = document.createElement('td');
			td.innerHTML = center[prop];
			tr.append(td);
		}
		document.querySelector('tbody').appendChild(tr);
	});
}
serviceRequest();
function serviceRequest(){
	url = 'https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=10&serviceKey=bxUPOlHD5Or%2BW2n3mSRy6VScgTwjRHVUcT%2B3uIMmVS9xLOUEcvU7wH41ooEVTiglYCJfpNAysDbNWQ7Pzqoj0Q%3D%3D';
	fetch(url)
	 	.then(result => {return result.json();})
	 	.then(arrayFunc)
	.catch(err => console.error());
}

document.querySelector('#selectCenter').addEventListener('change',function(e){
	sido = document.querySelector('#selectCenter option:checked').value;
	console.log(sido);
	serviceRequest();
});