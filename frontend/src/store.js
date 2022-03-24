import { createStore } from "redux";

const ADD = "ADD";

const addInfo = (infoObj) => {
    return { type: ADD, infoObj };
  };


const reducer = (state =[], action) => {
    switch (action.type) {
        case ADD:
         const newPollInfo = [
             {
                pollName: action.infoObj.pollName,
                pollPeriod: action.infoObj.pollPeriod,
                pollDescribe: action.infoObj.pollDescribe,
                pollRealtime: action.infoObj.pollRealtime,
                pollLatestTX: action.infoObj.pollLatestTX,
                pollAllTX: action.infoObj.pollAllTX,
                nomiList: action.infoObj.nomiList,
                status: action.infoObj.status,
             },
             ...state,
         ];
        //  console.log(newPollInfo);
        
         return newPollInfo;  

         default:
             return state;
    }
};


const store = createStore(reducer);



export const actionCreators = {
    addInfo
};

export default store;