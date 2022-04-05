//Accordion
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { useState } from 'react';
import Styles from "../../routes/Notice.module.css";

function NoticeU () {
    //accordian
    const [expanded, setExpanded] = useState(false);

    const handleChangeA = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };


    return(

        <div>
            <Accordion expanded={expanded === 'panel1'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel1')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1bh-content"
                    id="panel1bh-header"
                >
                    <div style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        기업회원 문의
                    </div>
                    {/* <Typography sx={{ color: 'text.secondary' }}>I am an accordion</Typography> */}
                </AccordionSummary>
                <AccordionDetails>
                    <div style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                        기업 회원은 관리자를 통해서만 가입이 가능합니다.<br/> 
                        사이트 하단의 이메일로 양식에 맞춰 연락 주시면 기업 회원 계정을 답신으로 받으실 수 있습니다. <br />
                        기업회원이 투표를 개설한 경우, 관리자의 승인 전까지는 사이트에 노출되지 않습니다.
                    </div>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel2'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel2')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel2bh-content"
                    id="panel2bh-header"
                >
                    <div style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        중복 회원 가입
                    </div>
                    {/* <Typography sx={{ color: 'text.secondary' }}>I am an accordion</Typography> */}
                </AccordionSummary>
                <AccordionDetails>
                    <div style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            저희 'POLLING'은 휴대폰인증 기반의 본인 인증이 이루어진 사용자만이 투표할 수 있도록 하고 있습니다. <br />
                            따라서 휴대폰 번호당 한 명만이 회원가입을 하실 수 있습니다.
                    </div>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel3'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel3')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel3bh-content"
                    id="panel3bh-header"
                >
                    <div style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        사용자 제공 기능
                    </div>
                    {/* <Typography sx={{ color: 'text.secondary' }}>I am an accordion</Typography> */}
                </AccordionSummary>
                <AccordionDetails>
                    <div style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            사용자들에게는 블록체인 기반 투표의 투명성, 신뢰 외에도 <br />
                            투표를 통해 마일리지를 적립하여, 추후 후보자들의 추가 이미지를 확인하실 수 있습니다.
                    </div>
                </AccordionDetails>
            </Accordion>
        </div>
    )
}

export default NoticeU;