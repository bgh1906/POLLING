import React, { useRef, useState } from "react";
import styles from "./Nominee.module.css";
import Swal from "sweetalert2";
import nopro from "../../assets/nopro.jpg"
import Button from '@mui/material/Button';


function NomineeInput({onAdd}) {
    const nameRef = useRef()

    const [form, setForm] = useState({
        name: '',
        profile: '',
        profile_image: '',
        additional_image1: '',
        additional_image2: '',
        additional_image3: '',
    })

    const changeInput=(e)=>{
        const{value, name} = e.target
        setForm({
            ...form,
            [name] : value
        })
    }

    const onSubmit=(e)=>{
        e.preventDefault()
        if(name !== '' || profile !== '' || profile_image !== ''){
            onAdd(form)
            setForm({
                name: '',
                profile: '',
                profile_image: '',
                additional_image1: '',
                additional_image2: '',
                additional_image3: '',
            })
            nameRef.current.focus()
        } else {
            Swal.fire({
                title: '참가자 정보를 입력해주세요!!',
                icon: "error"
            })
        }

    }
    const {name, profile, profile_image, additional_image1, additional_image2, additional_image3} = form


    return (
        <div className={styles.form_box}>
            <form id={styles.nomi_form} onSubmit={onSubmit}>
            {profile_image === '' 
            && ( <img id={styles.no_pro} src={nopro} alt="nopro"/>)}
            {profile_image !== '' 
            && ( <img id={styles.no_pro2} src={profile_image} alt="profile_image"/>)}
                <div id={styles.nomi_box}>
                    <p><label>이름 </label> : <input id={styles.nomi_input} type="text" onChange={changeInput} value={name} name="name" ref={nameRef} placeholder="참가자 이름을 등록하세요."/> </p>
                    <p><label>사진 </label> : <input id={styles.nomi_input2} type="text" onChange={changeInput} value={profile_image} name="profile_image" placeholder="참가자 사진을 등록하세요."/> </p>
                    <p>프로필 소개</p>  <p><textarea id={styles.nomi_input3} type="text" onChange={changeInput} value={profile} name="profile" maxLength="200"
                    placeholder="참가자 프로필 소개
                    EX) 수지 프로필
                    출생: 1994.10.10.     소속사: 매니지먼트 숲
                    데뷔: 2010년 MISSA 싱글[Bad But Good]"/></p>
                </div>
                <div id={styles.nomi_box2}>
                    <p>참가자 갤러리</p><p><input id={styles.nomi_input4} type="text" onChange={changeInput} value={additional_image1} name="additional_image1" placeholder="참가자의 매력을 보여주는 다양한 사진을 등록해주세요. (필수X)"/></p>
                    <p><input id={styles.nomi_input4} type="text" onChange={changeInput} value={additional_image2} name="additional_image2" placeholder="참가자의 매력을 보여주는 다양한 사진을 등록해주세요. (필수X)"/></p>
                    <p><input id={styles.nomi_input4} type="text" onChange={changeInput} value={additional_image3} name="additional_image3" placeholder="참가자의 매력을 보여주는 다양한 사진을 등록해주세요. (필수X)"/></p>
                    <p id={styles.form_buttonbox}>
                        <Button id={styles.register_button} type="submit" variant="contained"> 참가자 등록</Button>
                    </p>
                </div>
            </form>
        </div>
    );
}

export default NomineeInput;