// contracts/GLDToken.sol
// SPDX-License-Identifier: MIT

pragma solidity >=0.4.22 <0.9.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract CreateToken is ERC20 {
    constructor() ERC20("POLL", "POL") {
        _mint(msg.sender, 100_000_000_000 * 10**18);
    }
}