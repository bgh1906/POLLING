import NewNav from "../layout/NewNav";
import { styled } from '@mui/system';
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import * as React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionDetails from '@mui/material/AccordionDetails';
import AccordionSummary from '@mui/material/AccordionSummary';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Styles from './Qnalist.module.css';
import axios from "axios";
import Swal from "sweetalert2";

function QnaList2() {

    const token = sessionStorage.getItem("token")

    const [expanded, setExpanded] = React.useState(false);

    const handleChange = (panel) => (event, isExpanded) => {
      setExpanded(isExpanded ? panel : false);
    };

    //1:1문의 저장
    const [qnalist, setQnalist] = useState([]);

    useEffect(() => {
      let iscompomount = true;

      if( iscompomount === true ){
        axios
        .get(
          "https://j6a304.p.ssafy.io/api/contact",
          {
            headers: {
              "Authorization":token,
            },
          }
        )
        .then((res) => {
          setQnalist(res.data);
        })
        .catch(error => {
          console.log("res",error.response);
          console.log("error",error);
        })
      };
      return() => {
        iscompomount = false;
      };
    },[qnalist]);
 
    //1:1문의 삭제 성공
    const deleSuccess = () => {
      Swal.fire({
        title: "1:1문의 삭제 성공!!",
        text: "POLLING에 오신 것을 환영합니다!",
        icon: "success",
        confirmButtonColor: "#73E0C1",
        confirmButtonText: "확인",
      })
    };
      
    //1:1문의 삭제 실패
    const deleFail = () => {
      Swal.fire({
        title:"1:1문의 삭제 실패!",
        icon: 'error',
        confirmButtonColor: '#73E0C1',
        confirmButtonText: '확인'
      })
    }

    //1:1문의 삭제
    const qnadelet = (id) => {
      axios
        .delete(
          `/api/contact/${id}`,
          {
              headers: {
                "Authorization":token,
              },
          }
        )
        .then((res) => {
          console.log(res);
          deleSuccess();
                  
        })
        .catch((e) => {
          console.log("error,qnadelet",e);
          console.log("res,qnadelet",e.response);
          deleFail();
      });
    }

    return (
        <>
            {qnalist.map((index, key) => (
              <div key={index.id}>
                <Accordion 
                  className={Styles.accordion}
                  style={{marginBottom:'0vh', width:'45vw', left:'8vw', fontFamily:'GangwonEdu_OTFBoldA', backgroundColor:'rgba(250, 235, 215, 0.541)'}} 
                  expanded={expanded === `panel${index.id}`} 
                  onChange={handleChange(`panel${index.id}`)}
                  key={index}
                >
                  <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    key={key}
                  >
                    <div 
                      key={index.contactType}
                      style={{fontFamily:'RussoOne', fontSize:'1.2vw', lineHeight:'4vh'}} 
                      className={Styles.typotype} 
                      sx={{ width: '33%', flexShrink: 0 }}
                    >
                      [ {index.contactType.slice(8, 14)} ]
                    </div>
                    <div 
                      key={index.title}
                      maxLength="20"
                      style={{fontFamily:'GangwonEdu_OTFBoldA', fontSize:'1.3vw', paddingLeft:'5vw',paddingTop:'1vh',  fontWeight:'5vh'}}  
                      className={Styles.typoTitle} 
                      sx={{ color: 'text.secondary' }}
                    >
                      {index.title}
                    </div>
                  </AccordionSummary>
                  <AccordionDetails key={index}>
                    <div 
                      key={index.content} 
                      className={Styles.typoTitle} 
                      style={{wordBreak:'break-all', fontFamily:'GangwonEdu_OTFBoldA', paddingLeft:'1vw', fontSize:'1.3vw'}} 
                    >
                      {index.content}
                    </div>
                    <button 
                      key={index.id} 
                      value={index.id} 
                      onClick={() => {qnadelet(index.id)}} 
                      className={Styles.listbtn}
                      style={{fontFamily:'GangwonEdu_OTFBoldA', left:'39vw',borderRadius:'30vw', backgroundColor:'rgba(255, 194, 113, 0.452)'}} 
                    >
                      삭제
                    </button>
                  </AccordionDetails>
                </Accordion>
              </div>
            ))}
        </>
    );
}

export default QnaList2;