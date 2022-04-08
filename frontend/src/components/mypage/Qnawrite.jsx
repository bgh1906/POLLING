import { useState } from "react";
import NewNav from "../layout/NewNav";
import Styles from "./Qna.module.css"
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import axios from "axios";
import Swal from "sweetalert2";

function Qnawrite() {
    const token = sessionStorage.getItem("token")

    const qnafull = () => {
        Swal.fire({
            title:"모든 칸을 입력해주세요.",
            icon: 'error',
            confirmButtonColor: '#73E0C1',
            confirmButtonText: '확인'
        })
      }

    const qnaSuccess = () => {
        Swal.fire({
            text: "정상적으로 등록되었습니다.",
            icon: "success",
            confirmButtonColor: "#73E0C1",
            confirmButtonText: "확인",
        })
      };
      
      const qnaFail = () => {
        Swal.fire({
            title:"등록에 실패하였습니다.",
            icon: 'error',
            confirmButtonColor: '#73E0C1',
            confirmButtonText: '확인'
        })
      }

    //type 저장
    const [type, setType] = useState('');
    const handleChange = (event) => {
        setType(event.target.value);
    };
    
    //title 받기
    const [title, setTitle] = useState('');
    const  getTitle = (event) => {
        setTitle(event.target.value);
    };
    
    //email 받기
    const [email, setEmail] = useState('');
    const getEmail = (event) => {
        setEmail(event.target.value);
    };
    
    //내용 받기
    const [content, setContent] = useState('');
    const getContent = (event) => {
        setContent(event.target.value);
    };

    //글 등록시 내용 비우기
    const getClear = () => {
        setTitle('');
        setEmail('');
        setContent('');
    }

    //내용 전송하기
    const qna = () => {
        if(type === "" || title === "" || email === "" ||content === ""){
            qnafull()
        }else if(type !== "" || title !== "" || email !== "" ||content !== ""){
            axios
            .post(
                "https://j6a304.p.ssafy.io/api/contact",
                {
                    contactType: type,
                    content: content,
                    email: email,
                    title: title,
                },
                {
                    headers: {
                      "Authorization":token,
                    },
                }
            )
            .then((res) => {
                getClear();
                qnaSuccess();
            })
            .catch(error => {
                qnaFail();
            })
        }
    };

    return (
        <div className={Styles.div}>
        
            <div>
                <div className={Styles.title}>
                    TITLE
                </div>
                <input type={"text"} value={title} className={Styles.titleC} placeholder="제목을 입력하세요" onChange={getTitle}></input>
            </div>
            <div>
                <div className={Styles.email}>
                    E-MAIL
                </div>
                <input type={"email"} value={email} className={Styles.emailC} onChange={getEmail} placeholder="답변 받을 메일을 적어주세요"></input>
                    <FormControl variant="standard" className={Styles.type}>
                        <InputLabel id="demo-simple-select-standard-label" className={Styles.typetitle} >type</InputLabel>
                        <Select
                            labelId="demo-simple-select-standard-label"
                            id="demo-simple-select-standard"
                            value={type}
                            onChange={handleChange}
                            label="type"
                            className={Styles.typetext}
                        >
                            <MenuItem value='기업' className={Styles.typetext}>기업</MenuItem>
                            <MenuItem value='투표' className={Styles.typetext}>투표</MenuItem>
                            <MenuItem value='회원' className={Styles.typetext}>회원</MenuItem>
                            <MenuItem value='NFT' className={Styles.typetext}>NFT</MenuItem>
                            <MenuItem value='기타' className={Styles.typetext}>기타</MenuItem>
                        </Select>
                    </FormControl>
            </div>
            <div >
                <div className={Styles.content}>
                    CONTENT
                </div>
                <textarea value={content}  onChange={getContent} className={Styles.contentC}  placeholder="내용을 입력하세요"></textarea>
            </div>
            <button className={Styles.save} onClick={qna}>submit</button>
        </div>
    );
}

export default Qnawrite;