const Web3 = require('web3');

/* REPLACE: 노드 엔드포인트 문자열 */
const ENDPOINT = 'http://localhost:7545';

const web3 = new Web3(new Web3.providers.HttpProvider(ENDPOINT));

const abi = [
	{
		"inputs": [
			{
				"internalType": "string",
				"name": "candidateNames",
				"type": "string"
			}
		],
		"name": "addCandidate",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "string[]",
				"name": "candidateNames",
				"type": "string[]"
			}
		],
		"name": "addCandidates",
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
				"internalType": "string",
				"name": "",
				"type": "string"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "string",
				"name": "candidate",
				"type": "string"
			}
		],
		"name": "totalVotesFor",
		"outputs": [
			{
				"internalType": "uint8",
				"name": "",
				"type": "uint8"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "string",
				"name": "candidate",
				"type": "string"
			}
		],
		"name": "validCandidate",
		"outputs": [
			{
				"internalType": "bool",
				"name": "",
				"type": "bool"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "string",
				"name": "userId",
				"type": "string"
			},
			{
				"internalType": "string",
				"name": "candidate",
				"type": "string"
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
				"internalType": "string",
				"name": "",
				"type": "string"
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
	},
	{
		"inputs": [
			{
				"internalType": "string",
				"name": "",
				"type": "string"
			}
		],
		"name": "votesTracker",
		"outputs": [
			{
				"internalType": "string",
				"name": "",
				"type": "string"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
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
		"name": "votesTrackersss",
		"outputs": [
			{
				"internalType": "string",
				"name": "",
				"type": "string"
			}
		],
		"stateMutability": "view",
		"type": "function"
	}
];

const call = () => {
    const CONTRACT_ADDRESS = '0xe982E462b094850F12AF94d21D470e21bE9D0E9C';
    const address = '0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1';
    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.candidateList(0).call({ from: address }).then(console.log);
};

const send = () => {
    const CONTRACT_ADDRESS = '0xe982E462b094850F12AF94d21D470e21bE9D0E9C';
    const address = '0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1';
    const testContract = new web3.eth.Contract(abi, CONTRACT_ADDRESS);

    testContract.methods.addCandidate("지환").send({ from: address }).then(console.log);
};

// send();
call();
