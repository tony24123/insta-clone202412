async function fetchToLogin(userData) {
  await fetch("/user/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(userData),
  });
  console.log("로그인!");
  alert("로그인 성공");
  document.querySelector(".bj-login-form").reset();
}

function initLogin() {
  const $form = document.querySelector(".bj-login-form");
  $form.addEventListener("submit", (e) => {
    e.preventDefault();

    const username = document.querySelector('input[name ="username"]').value;
    const password = document.querySelector('input[name ="password"]').value;

    const payload = {
      username: username,
      password: password,
    };

    //
    fetchToLogin(payload);
  });
}

document.addEventListener("DOMContentLoaded", initLogin);
