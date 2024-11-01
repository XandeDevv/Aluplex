function showError() {
    alert("Credenciais incorretas. Tente novamente.");
}

function login(event) {
    event.preventDefault();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password })
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Obtemos o corpo da resposta como JSON
        } else {
            showError();
        }
    })
    .then(data => {
        if (data) {
            localStorage.setItem("jwt", data.token); // Armazena o token no localStorage
            console.log("Login bem-sucedido!", data.token);
            // Redirecionar ou realizar outra ação após o login
        }
    })
    .catch(error => {
        console.error("Erro:", error);
    });
}