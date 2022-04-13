const Polling = artifacts.require("Polling");

module.exports = function(deployer) {
  deployer.deploy(Polling);
};