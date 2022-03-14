import Nav from "../components/layout/Nav.jsx"


function Login() {

    return (
        <>
            <Nav/>
            <div>Sign in</div>
            <div>
                <form>
                    <input type="text" placeholder="E-mail"/>
                    <input type="password" placeholder="Password"/>
                    <button></button>
                </form>
                <div></div>


            </div>
        </>
    );
}

export default Login;