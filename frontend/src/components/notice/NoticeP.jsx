//Accordion
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { useState } from 'react';
import Styles from "../../routes/Notice.module.css";

function NoticeP () {
    //accordian
    const [expanded, setExpanded] = useState(false);

    const handleChangeA = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };


    return(

        <div>
            <Accordion expanded={expanded === 'panel1'} style={{backgroundColor:"#3afaca15"}}  onChange={handleChangeA('panel1')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1bh-content"
                    id="panel1bh-header"
                    className={Styles.aaccordioncolor}
                    
                >
                    <div sx={{ width: '33%', flexShrink: 0 }} style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} className={Styles.aaccordiontitle}>
                        투표 방법
                    </div>
                </AccordionSummary>
                <AccordionDetails>
                    <div style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                        투표 방법 <br />
                        (1) 저희 사이트에 회원가입/로그인해 주세요(모든 빈칸들을 입력해주셔야 합니다)<br />
                        (2) 우측 메뉴에서 'POLL'로 이동해주세요 <br />
                        (3) 원하시는 대회를 선택하시고, 후보자 확인 후 투표를 진행해주세요 <br />
                        (4) 투표 내역이 블록체인으로 전송되며, 투명하게 관리됩니다.
                    </div>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel2'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel2')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel2bh-content"
                    id="panel2bh-header"
                >
                    <div sx={{ width: '33%', flexShrink: 0 }} style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} className={Styles.aaccordiontitle}>
                        투표 프로세스
                    </div>
                </AccordionSummary>
                <AccordionDetails>
                    <div style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            (1) 유권자는 사이트 회원가입을 통해 본인 인증을 진행해주세요. <br />
                            (2) 'POLL'의 투표 리스트에서 원하는 투표와 후보자를 선택, 투표하기를 눌러주세요. <br />
                            (3) 유권자의 투표 내역이 블록체인으로 전송되어 기록됩니다.(위변조가 불가능합니다.) <br />
                            (4) 트랜잭션ID를 통해 투표가 기록되었음을 확인하실 수 있습니다.
                    </div>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel3'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel3')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel3bh-content"
                    id="panel3bh-header"
                >
                    <div sx={{ width: '33%', flexShrink: 0 }} style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} className={Styles.aaccordiontitle}>
                        투표 결과
                    </div>
                </AccordionSummary>
                <AccordionDetails>
                    <div style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            투표 결과는 모두 블록체인에 기록되며, 해당 대회에서도 표수를 확인 하실 수 있습니다.
                    </div>
                </AccordionDetails>
            </Accordion>
                
        </div>
    )
}

export default NoticeP;