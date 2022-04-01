// contracts/GLDToken.sol
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.7;

import "@openzeppelin/contracts/token/ERC20/extensions/ERC20Burnable.sol";
import "@openzeppelin/contracts/token/ERC20/ERC20.sol";


contract pollingToken is ERC20 {
    constructor() ERC20("POLL", "POL") {
        _mint(msg.sender, 100_000_000_000 * 10**18);
    }
}