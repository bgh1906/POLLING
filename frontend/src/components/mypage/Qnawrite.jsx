import { useState } from "react";
import NewNav from "../layout/NewNav";
import Styles from "./Qna.module.css"
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';


function Qna({getWrite}) {

    //type 저장
    const [type, setType] = useState('');
    const handleChange = (event) => {
        setType(event.target.value);
    };
    // console.log(type);

    const [list, setList] = useState(false);
    const getList = () => {
        getWrite(list);

    }

    return (
        <div className={Styles.div}>
        
        {/* <NewNav /> */}
            <div>
                <div className={Styles.title}>
                    TITLE
                </div>
                <input type={"text"} className={Styles.titleC} placeholder="제목을 입력하세요"></input>
            </div>
            <div>
                <div className={Styles.email}>
                    E-MAIL
                </div>
                <input type={"email"} className={Styles.emailC}  placeholder="답변 받을 메일을 적어주세요"></input>
                    {/* <FormControl variant="standard" sx={{ minWidth: 250 }} className={Styles.type}> */}
                    <FormControl variant="standard" className={Styles.type}>
                        <InputLabel id="demo-simple-select-standard-label" className={Styles.typetitle}  >type</InputLabel>
                        <Select
                            labelId="demo-simple-select-standard-label"
                            id="demo-simple-select-standard"
                            value={type}
                            onChange={handleChange}
                            label="type"
                            className={Styles.typetext}
                        >
                        {/* <MenuItem value="">
                            <em>None</em>
                        </MenuItem> */}
                        <MenuItem value="company" className={Styles.typetext}>company</MenuItem>
                        <MenuItem value='poll' className={Styles.typetext}>poll</MenuItem>
                        <MenuItem value='ticket' className={Styles.typetext}>ticket</MenuItem>
                        </Select>
                    </FormControl>
            </div>
            <div >
                <div className={Styles.content}>
                    CONTENT
                </div>
                <textarea className={Styles.contentC}  placeholder="내용을 입력하세요"></textarea>
            </div>
            {/* <button className={Styles.list} onClick={getList}>목록</button> */}
            <button className={Styles.save}>submit</button>
        </div>
    );
}

export default Qna;