const Web3 = require("web3");

/* REPLACE: 노드 엔드포인트 문자열 */
const ENDPOINT = "https://j6a304.p.ssafy.io/block/";
export const web3 = new Web3(new Web3.providers.HttpProvider(ENDPOINT));

//컨트랙트 배포주소

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

const POLL_ADDRESS = "0x78654a144866112e724Ce2e5a85aa072656917d6";
// EC2에서 배포된 CA : 0xCfEB869F69431e42cdB54A4F4f105C19C080A601
//트랜젝션 보내는 유저지갑주소

//후보자 인덱스와 후보자명 확인
// 프론트와 연동할 때는 안쓸 함수, 단순 체크용
const pollContract = new web3.eth.Contract(abi, POLL_ADDRESS);
export const getCandInfoBlock = (account) => {
  pollContract.methods
    .candidateList(1)
    .call({ from: account })
    .then(console.log);
};

//후보자 등록
export const registerBlock = (num) => {
  return pollContract.methods
    .addCandidates(num)
    .send({ from: "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1", gas: 1000000 });
};
// add 에서 return값 0,9 받아오는 함수 지환님이  새로 작성해서 넘겨주실 예정

//투표 후보자 인덱스에게 몇표 투표 진행
export const voteBlock = (idx, account) => {
  return (
    pollContract.methods
      .voteForCandidate(idx, 1)
      // 여기서 2가 candidateIndex
      // 투표하기전에 특정후보자 조회에서 Index 받아오기
      .send({ from: account })
  );
  // 트랜잭션 id 리턴해줌 = transaction hash
};

//후보자 인덱스 입력시 득표수 반환
export const totalVotesBlock = (idx, account) => {
  return pollContract.methods.votesReceived(idx).call({ from: account });
};

//후보자 인덱스 반환
export const getStartIndexBlock = (account) => {
  return pollContract.methods.candidateIndexStart().call({ from: account });
};
// // 유저에게 비밀번호를 입력받아 지갑주소을 생성하고 해당비밀번호는 관리자가 알수없다.
// // 주의사항 명시 계좌 비밀번호는 사이트에서 관리하지않습니다 잊어버리는경우 알수없으니 보관시 주의 바랍니다.
// // 유저 회원가입시 투표계좌생성
// var addressPassword = "유저가 입력"

// 1.유저가 회원가입에서 유저 정보를 입력한다.(계좌비밀번호)
// 2.회원가입 버튼을 클릭하면
// 2 - 1.블록체인서버와 통신하여 지갑주소를 생성하고 계좌주소를 받아온다.
// 2-2.유저 정보를 회원가입 api에 넣어준다.
export const unlockAccount = (account, password) => {
  //계정 UNLOCK
  //db에서 유저와 연동된 지갑주소를 가지고와서 account에 넣어준다.

  web3.eth.personal.unlockAccount(account, password).then(console.log);
};
export const lockAccount = (account) => {
  //계정 LOCK
  //투표가 끝나면 해당유저의 지갑주소를 LOCK하여 트랜잭션을 날리지 못하게 변경한다.
  web3.eth.personal.lockAccount(account).then(console.log);
};

// 코인 발급 CA : 0x946E9E14A281A8F69A2a248EE0575a2b76D69D45
//이더 전송해주기
// 가스비가 든다는 가정 하에 사용자가 투표하기 누를 때마다 잔고 확인해서 부족하면 보내주는 함수
export const sendEth = () => {
  const baseAddress = "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1";
  const userAddress = "0x82769faAC683cF4AE8A5846B49e83414772686D3";

  //트랜잭션 생성 보내는주소, 받는주소, 이더수량
  const tx = { from: baseAddress, to: userAddress, value: 1e16 };
  //트랜잭션 전송
  web3.eth.sendTransaction(tx);
};

const tokenAbi = [
  {
    inputs: [
      {
        internalType: "string",
        name: "name_",
        type: "string",
      },
      {
        internalType: "string",
        name: "symbol_",
        type: "string",
      },
    ],
    stateMutability: "nonpayable",
    type: "constructor",
  },
  {
    anonymous: false,
    inputs: [
      {
        indexed: true,
        internalType: "address",
        name: "owner",
        type: "address",
      },
      {
        indexed: true,
        internalType: "address",
        name: "spender",
        type: "address",
      },
      {
        indexed: false,
        internalType: "uint256",
        name: "value",
        type: "uint256",
      },
    ],
    name: "Approval",
    type: "event",
  },
  {
    anonymous: false,
    inputs: [
      {
        indexed: true,
        internalType: "address",
        name: "from",
        type: "address",
      },
      {
        indexed: true,
        internalType: "address",
        name: "to",
        type: "address",
      },
      {
        indexed: false,
        internalType: "uint256",
        name: "value",
        type: "uint256",
      },
    ],
    name: "Transfer",
    type: "event",
  },
  {
    inputs: [
      {
        internalType: "address",
        name: "owner",
        type: "address",
      },
      {
        internalType: "address",
        name: "spender",
        type: "address",
      },
    ],
    name: "allowance",
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
        internalType: "address",
        name: "spender",
        type: "address",
      },
      {
        internalType: "uint256",
        name: "amount",
        type: "uint256",
      },
    ],
    name: "approve",
    outputs: [
      {
        internalType: "bool",
        name: "",
        type: "bool",
      },
    ],
    stateMutability: "nonpayable",
    type: "function",
  },
  {
    inputs: [
      {
        internalType: "address",
        name: "account",
        type: "address",
      },
    ],
    name: "balanceOf",
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
    name: "decimals",
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
  {
    inputs: [
      {
        internalType: "address",
        name: "spender",
        type: "address",
      },
      {
        internalType: "uint256",
        name: "subtractedValue",
        type: "uint256",
      },
    ],
    name: "decreaseAllowance",
    outputs: [
      {
        internalType: "bool",
        name: "",
        type: "bool",
      },
    ],
    stateMutability: "nonpayable",
    type: "function",
  },
  {
    inputs: [
      {
        internalType: "address",
        name: "spender",
        type: "address",
      },
      {
        internalType: "uint256",
        name: "addedValue",
        type: "uint256",
      },
    ],
    name: "increaseAllowance",
    outputs: [
      {
        internalType: "bool",
        name: "",
        type: "bool",
      },
    ],
    stateMutability: "nonpayable",
    type: "function",
  },
  {
    inputs: [],
    name: "name",
    outputs: [
      {
        internalType: "string",
        name: "",
        type: "string",
      },
    ],
    stateMutability: "view",
    type: "function",
  },
  {
    inputs: [],
    name: "symbol",
    outputs: [
      {
        internalType: "string",
        name: "",
        type: "string",
      },
    ],
    stateMutability: "view",
    type: "function",
  },
  {
    inputs: [],
    name: "totalSupply",
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
        internalType: "address",
        name: "to",
        type: "address",
      },
      {
        internalType: "uint256",
        name: "amount",
        type: "uint256",
      },
    ],
    name: "transfer",
    outputs: [
      {
        internalType: "bool",
        name: "",
        type: "bool",
      },
    ],
    stateMutability: "nonpayable",
    type: "function",
  },
  {
    inputs: [
      {
        internalType: "address",
        name: "from",
        type: "address",
      },
      {
        internalType: "address",
        name: "to",
        type: "address",
      },
      {
        internalType: "uint256",
        name: "amount",
        type: "uint256",
      },
    ],
    name: "transferFrom",
    outputs: [
      {
        internalType: "bool",
        name: "",
        type: "bool",
      },
    ],
    stateMutability: "nonpayable",
    type: "function",
  },
];

const TOKEN_ADDRESS = "0xA507B8a9b20f72EAE9d93818e9d0E3FB1878B23E";

//POL토큰 전송 과정
export const tokenContract = new web3.eth.Contract(tokenAbi, TOKEN_ADDRESS);

//보낼주소에서 val만큼의 토큰을 전송가능하도록 승인해준다.
export const approveAccount = (val, fromAddress) => {
  // const val = 100; //보내는것을 승인할 금액
  //account는 관리자주소
  return tokenContract.methods
    .approve(fromAddress, val)
    .send({ from: fromAddress });
};

//보내는주소에서 받는주소로 val만큼의 POL토큰을 보내준다.
export const sendPOL = (val, fromAddress, toAddress) => {
  //account는 관리자주소
  return (
    tokenContract.methods
      .transferFrom(fromAddress, toAddress, val)
      // .send({ from: fromAddress }); //토큰 받는 것은 됨
      .send({ from: fromAddress })
  ); //토큰 받는 것은 됨
};

// 사용자 주소의 잔액 확인 (잔액 바뀔때마다 리렌더링 필요)
export const checkPOL = (wallet) => {
  return tokenContract.methods
    .balanceOf(wallet)
    .call({ from: "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1" });
};
