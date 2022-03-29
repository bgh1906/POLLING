const Web3 = require('web3');

/* REPLACE: 노드 엔드포인트 문자열 */
const ENDPOINT = 'http://localhost:7545';

const web3 = new Web3(new Web3.providers.HttpProvider(ENDPOINT));

const abi = [
	{
		"inputs": [
			{
				"internalType": "uint32",
				"name": "candidateNum",
				"type": "uint32"
			}
		],
		"name": "addCandidates",
		"outputs": [
			{
				"internalType": "uint32",
				"name": "",
				"type": "uint32"
			},
			{
				"internalType": "uint32",
				"name": "",
				"type": "uint32"
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





const CONTRACT_ADDRESS = '0x630589690929E9cdEFDeF0734717a9eF3Ec7Fcfe';
const address = '0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1';

//후보자 인덱스와 후보자명 확인
const call = () => {
    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.candidateList(0).call({ from: address }).then(console.log);
};

//후보자 등록
const send = () => {

    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.addCandidates(100).send({ from: address , gas: 3000000}).then(console.log);
};

//투표 후보자 인덱스에게 몇표 투표 진행
const vote = () => {
    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.voteForCandidate(0, 3).send({ from: address }).then(console.log);
};

//후보자 인덱스 입력시 득표수 반환
const votesReceived = () => {
    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.votesReceived(0).call({ from: address }).then(console.log);
};

send();
// call();
// vote();
// votesReceived();

