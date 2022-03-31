
export const Web3 = require('web3');

/* REPLACE: 노드 엔드포인트 문자열 */
export const ENDPOINT = 'http://localhost:7545';

export const web3 = new Web3(new Web3.providers.HttpProvider(ENDPOINT));

export const abi = [
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "candidateNum",
				"type": "uint256"
			}
		],
		"name": "addCandidates",
		"outputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			},
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [],
		"stateMutability": "nonpayable",
		"type": "constructor"
	},
	{
		"inputs": [
			{
				"internalType": "uint8",
				"name": "candidateIndex",
				"type": "uint8"
			},
			{
				"internalType": "uint8",
				"name": "voteCount",
				"type": "uint8"
			}
		],
		"name": "voteForCandidate",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"name": "candidateList",
		"outputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint8",
				"name": "",
				"type": "uint8"
			}
		],
		"name": "votesReceived",
		"outputs": [
			{
				"internalType": "uint8",
				"name": "",
				"type": "uint8"
			}
		],
		"stateMutability": "view",
		"type": "function"
	}
];


const CONTRACT_ADDRESS = '0x0290FB167208Af455bB137780163b7B7a9a10C16';
const address = '0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1';

//후보자 인덱스와 후보자명 확인
const call = () => {
    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.candidateList(0).call({ from: address }).then(console.log);
};

//후보자 등록
const send = () => {

    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.addCandidates(1).send({ from: address}).then(console.log);
};

//투표 후보자 인덱스에게 몇표 투표 진행
const vote = () => {
    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.voteForCandidate(0, 3).send({ from: address }).then(console.log);
};

//후보자 인덱스 입력시 득표수 반환
const votesReceived = () => {
    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.votesReceived(2).call({ from: address }).then(console.log);
};

// send();
// call();
// vote();
// votesReceived();






// 


// <!DOCTYPE html>
// <html>
// <head>
//   <meta charset="utf-8">
//   <title>Web3.js 계정 생성</title>
// </head>

// <script src="https://cdn.jsdelivr.net/gh/ethereum/web3.js@1.0.0-beta.8/dist/web3.min.js"></script>
 
// <body>
 
// * 비밀번호: <input id="passwd" type="text" style="width: 200px;" value=""><br><br>
// <button onclick="createAccount()">생성</button><br><br>
// * 진행상태: <span id="status"></span><br>
// * 계정 주소: <span id="account_addr"></span>

// <script>

// 	// var Web3 = require("web3");
// 	var web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545")); 
	
// 	document.getElementById('status').innerHTML = "Ready";
		
// 	function createAccount() {
// 		var password = document.getElementById('passwd').value;
// 		if (password == "") {
// 			alert("비밀번호를 입력하세요!");
// 				return;
// 		}
// 		document.getElementById('status').innerHTML = "Pending";
// 		web3.eth.personal.newAccount(password, function(err,r){
// 			document.getElementById('account_addr').innerHTML = r;
// 			document.getElementById('status').innerHTML = "Complete";
// 			console.log(r);
// 		});	
// 	}
	
// </script>
// </body>
// </html>