//회원가입 정보를 서버에 전송
async function fetchToSignUp(userData){
    await fetch('/user/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json'},
        body: JSON.stringify(userData)
    });
    
}

//초기화 함수
function initSignUp() {

    //form submit 이벤트
    const $form = document.querySelector('.register-form');
    $form.addEventListener('submit', e => {
        e.preventDefault();

        const username = document.querySelector('input[name="username"]').value;
        const password = document.querySelector('input[name="password"]').value;
        const email = document.querySelector('input[name="email"]').value;
        const passwordConfirm = document.querySelector('input[name="password-confirm"]').value;    
    
        const payload = {
            username : username ,
            email : email ,
            password: password
        };

        //서버로 데이터 전송송
        fetchToSignUp(payload);

    });

    
}


//메인 실행 코드
document.addEventListener('DOMContentLoaded', initSignUp);