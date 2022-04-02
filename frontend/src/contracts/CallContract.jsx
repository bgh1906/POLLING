const Web3 = require("web3");
// import Web3 from "web3";

/* REPLACE: 노드 엔드포인트 문자열 */
const ENDPOINT = "http://localhost:7545";

export const web3 = new Web3(new Web3.providers.HttpProvider(ENDPOINT));

const abi = [
  {
    inputs: [],
    stateMutability: "nonpayable",
    type: "constructor",
  },
  {
    inputs: [
      {
        internalType: "uint256",
        name: "candidateNum",
        type: "uint256",
      },
    ],
    name: "addCandidates",
    outputs: [
      {
        internalType: "uint256",
        name: "",
        type: "uint256",
      },
      {
        internalType: "uint256",
        name: "",
        type: "uint256",
      },
    ],
    stateMutability: "nonpayable",
    type: "function",
  },
  {
    inputs: [
      {
        internalType: "uint256",
        name: "",
        type: "uint256",
      },
    ],
    name: "candidateList",
    outputs: [
      {
        internalType: "uint256",
        name: "",
        type: "uint256",
      },
    ],
    stateMutability: "view",
    type: "function",
  },
  {
    inputs: [
      {
        internalType: "uint8",
        name: "candidateIndex",
        type: "uint8",
      },
      {
        internalType: "uint8",
        name: "voteCount",
        type: "uint8",
      },
    ],
    name: "voteForCandidate",
    outputs: [],
    stateMutability: "nonpayable",
    type: "function",
  },
  {
    inputs: [
      {
        internalType: "uint8",
        name: "",
        type: "uint8",
      },
    ],
    name: "votesReceived",
    outputs: [
      {
        internalType: "uint8",
        name: "",
        type: "uint8",
      },
    ],
    stateMutability: "view",
    type: "function",
  },
];

//컨트랙트 배포주소
const CONTRACT_ADDRESS = "0xe78A0F7E598Cc8b0Bb87894B0F60dD2a88d6a8Ab";
//트랜젝션 보내는 유저지갑주소
const account = "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1";

//후보자 인덱스와 후보자명 확인
export const getCandInfo = () => {
  const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);
  testContract.methods
    .candidateList(0)
    .call({ from: account })
    .then(console.log);
};

//후보자 등록
export const add = () => {
  const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);
  testContract.methods
    .addCandidates(1)
    .send({ from: account })
    .then(console.log);
};

//투표 후보자 인덱스에게 몇표 투표 진행
export const vote = () => {
  const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);
  testContract.methods
    .voteForCandidate(2, 10)
    .send({ from: account })
    .then(console.log);
};

//후보자 인덱스 입력시 득표수 반환
export const votesReceived = () => {
  const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);
  testContract.methods
    .votesReceived(2)
    .call({ from: account })
    .then(console.log);
};

// // 유저에게 비밀번호를 입력받아 지갑주소을 생성하고 해당비밀번호는 관리자가 알수없다.
// // 주의사항 명시 계좌 비밀번호는 사이트에서 관리하지않습니다 잊어버리는경우 알수없으니 보관시 주의 바랍니다.
// // 유저 회원가입시 투표계좌생성
// var addressPassword = "유저가 입력"
// web3.eth.personal.newAccount(addressPassword).then(console.log(account));

// 1.유저가 회원가입에서 유저 정보를 입력한다.(계좌비밀번호)
// 2.회원가입 버튼을 클릭하면
// 2 - 1.블록체인서버와 통신하여 지갑주소를 생성하고 계좌주소를 받아온다.
// 2-2.유저 정보를 회원가입 api에 넣어준다.
// account = "0x82769faAC683cF4AE8A5846B49e83414772686D3";
export const unlockAccount = () => {
  //계정 UNLOCK
  //db에서 유저와 연동된 지갑주소를 가지고와서 account에 넣어준다.

  // password = "123";
  web3.eth.personal
    .unlockAccount(account, 123)
    .catch(console.log("Account unlocked!"));
};
export const lockAccount = () => {
  //계정 LOCK
  //투표가 끝나면 해당유저의 지갑주소를 LOCK하여 트랜잭션을 날리지 못하게 변경한다.
  web3.eth.personal.lockAccount(account).then(console.log("Account locked!"));
};

//이더 전송해주기
//투표시 소요되는 이더만큼 전송해줌?
// baseAddress = "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1";
// userAddress = "0x82769faAC683cF4AE8A5846B49e83414772686D3";

// //트랜잭션 생성 보내는주소, 받는주소, 이더수량
// tx = { from: baseAddress, to: userAddress, value: 1e16 }
// //트랜잭션 전송
// web3.eth.sendTransaction(tx);
// //투표할때 잠깐열어주기

// send();
// call();
// vote();
// votesReceived();

// unlock();
// lock();

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
