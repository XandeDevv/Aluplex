document.getElementById('loginForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const loginRequest = {
        email: email,
        password: password,
    };

    try {
        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(loginRequest),
        });

        const errorBox = document.getElementById('error-box');

        if (response.ok) {
            alert('Login bem-sucedido!');
            errorBox.style.display = 'none'; // Oculta a caixa de erro
        } else if (response.status === 401) {
            errorBox.style.display = 'block'; // Exibe a caixa de erro
            errorBox.textContent = 'Credenciais incorretas';
        } else {
            console.error('Erro ao fazer login:', response.statusText);
            errorBox.style.display = 'none'; // Oculta a caixa de erro
        }
    } catch (error) {
        console.error('Erro ao fazer login:', error);
        alert('Ocorreu um erro ao tentar fazer login.');
    }
});