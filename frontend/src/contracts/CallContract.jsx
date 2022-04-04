const Web3 = require("web3");
// import Web3 from "web3";

/* REPLACE: 노드 엔드포인트 문자열 */
const ENDPOINT = "http://localhost:7545";

export const web3 = new Web3(new Web3.providers.HttpProvider(ENDPOINT));

//컨트랙트 배포주소

export const abi = [
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
    inputs: [],
    name: "candidateIndexEnd",
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
    inputs: [],
    name: "candidateIndexStart",
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
    inputs: [],
    name: "checkCandidateIndex",
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

export const CONTRACT_ADDRESS = "0x9a74f110586971345A396C74228094A04f5A5eA6";
// EC2에서 배포된 CA : 0xCfEB869F69431e42cdB54A4F4f105C19C080A601
//트랜젝션 보내는 유저지갑주소
export const account = "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1";

//후보자 인덱스와 후보자명 확인
// 프론트와 연동할 때는 안쓸 함수, 단순 체크용
export const getCandInfoBlock = () => {
  const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);
  testContract.methods
    .candidateList(1)
    .call({ from: account })
    .then(console.log);
};
// getCandInfo();

//후보자 등록
export const registerBlock = (num) => {
  const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);
  const regi = testContract.methods
    .addCandidates(num)
    .send({ from: account, gas: 1000000 });
  // .then(console.log);
  return regi;
};
// add 에서 return값 0,9 받아오는 함수 지환님이  새로 작성해서 넘겨주실 예정
// send();

//투표 후보자 인덱스에게 몇표 투표 진행
export const voteBlock = (idx) => {
  const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);
  const vote = testContract.methods
    .voteForCandidate(idx, 1)
    // 여기서 2가 candidateIndex
    // 투표하기전에 특정후보자 조회에서 Index 받아오기
    .send({ from: account });
  return vote;
  // .then(console.log);
  // 트랜잭션 id 리턴해줌 = transaction hash
};
// vote();

//후보자 인덱스 입력시 득표수 반환
export const totalVotesBlock = () => {
  const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);
  testContract.methods
    .votesReceived(0)
    .call({ from: account })
    .then(console.log);
};
// votesReceived();

//후보자 인덱스 반환
export const getStartIndexBlock = () => {
  const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);
  const Index = testContract.methods
    .candidateIndexStart()
    .call({ from: account });
  return Index;
};
// candidateIndexStart();

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

// 코인 발급 CA : 0xC89Ce4735882C9F0f0FE26686c53074E09B0D550
//이더 전송해주기
export const sendEth = () => {
  const baseAddress = "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1";
  const userAddress = "0x82769faAC683cF4AE8A5846B49e83414772686D3";

  //트랜잭션 생성 보내는주소, 받는주소, 이더수량
  const tx = { from: baseAddress, to: userAddress, value: 1e16 };
  //트랜잭션 전송
  web3.eth.sendTransaction(tx);
};

// send();
// call();
// getCandInfo();
// add();
// vote();
// votesReceived();

// unlock();
// lock();
