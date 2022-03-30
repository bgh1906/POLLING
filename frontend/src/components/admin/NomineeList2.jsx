import Nominee2 from "./Nominee2";
import React, { useEffect } from "react";



function NomineeList({ nomiList, onDel, onEdit, deleteCandi}) {

    useEffect(() =>{

        if(nomiList[0].id === 0){
            onDel(0);
        }
    }, [])

    return (
         <ul style={{ padding:"0" }}>
             {
                 nomiList.map((nominee,index) => <Nominee2 key={index} nominee={nominee} onDel={onDel} onEdit={onEdit} deleteCandi={deleteCandi}/>)
             }
         </ul>

    );
}

export default NomineeList;