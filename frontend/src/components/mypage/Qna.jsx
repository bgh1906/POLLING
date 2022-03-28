import { useState } from "react";
import NewNav from "../layout/NewNav";



function Qna({write}) {

    const [list, setList] = useState(false);
    const getList = () => {
        setList(!write);
    }

    return (
        <>
        
        {/* <NewNav /> */}
        제목 : <input></input>
            <br />
            내용 : <textarea></textarea>
            <button onClick={getList}>목록</button>
            <button>저장</button>
        </>
    );
}

export default Qna;