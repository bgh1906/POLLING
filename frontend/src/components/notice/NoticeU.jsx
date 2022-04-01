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
                    <Typography style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        기업회원 문의
                    </Typography>
                    {/* <Typography sx={{ color: 'text.secondary' }}>I am an accordion</Typography> */}
                </AccordionSummary>
                <AccordionDetails>
                    <Typography style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                    기업 회원은 관리자를 통해서만 가입이 가능합니다.<br/> 
                    사이트 하단의 이메일로 양식에 맞춰 연락 주시면 기업 회원 계정을 답신으로 받으실 수 있습니다. <br />
                    기업회원이 투표를 개설한 경우, 관리자의 승인 전까지는 사이트에 노출되지 않습니다
                    </Typography>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel2'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel2')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel2bh-content"
                    id="panel2bh-header"
                >
                    <Typography style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        중복 회원 가입
                    </Typography>
                    {/* <Typography sx={{ color: 'text.secondary' }}>I am an accordion</Typography> */}
                </AccordionSummary>
                <AccordionDetails>
                    <Typography style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            Nulla facilisi. Phasellus sollicitudin nulla et quam mattis feugiat.
                            Aliquam eget maximus est, id dignissim quam.
                    </Typography>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel3'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel3')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel3bh-content"
                    id="panel3bh-header"
                >
                    <Typography style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        사용자 제공 기능
                    </Typography>
                    {/* <Typography sx={{ color: 'text.secondary' }}>I am an accordion</Typography> */}
                </AccordionSummary>
                <AccordionDetails>
                    <Typography style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            Nulla facilisi. Phasellus sollicitudin nulla et quam mattis feugiat.
                            Aliquam eget maximus est, id dignissim quam.
                    </Typography>
                </AccordionDetails>
            </Accordion>
        </div>
    )
}

export default NoticeU;