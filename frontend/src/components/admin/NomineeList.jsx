import Nominee from "./Nominee";
import React, { useEffect } from "react";



function NomineeList({ nomiList, onDel, onEdit }) {

    useEffect(() =>{

        if(nomiList[0].id === 0){
            onDel(0);
        }
    }, [])

    return (
         <ul style={{ padding:"0" }}>
             {
                 nomiList.map(nominee => <Nominee key={nominee.id} nominee={nominee} onDel={onDel} onEdit={onEdit}/>)
             }
         </ul>

    );
}

export default NomineeList;