document.getElementById('insertForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const cpf = document.getElementById('cpf').value;
    const password = document.getElementById('password').value;
    const role = document.querySelector('input[name="role"]:checked')?.value;

    const userInsertDto = {
        name: name,
        email: email,
        cpf: cpf,
        password: password,
        role: role // Obtém o valor do radio selecionado
    };

    try {
        const response = await fetch('http://localhost:8080/auth/insert', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userInsertDto),
        });
    
        if (response.ok) {
            alert('Usuário inserido com sucesso!');
            document.getElementById('insertForm').reset(); // Reseta o formulário
        } else {
            const errorData = await response.json();
            handleValidationError(errorData); // Exibe os erros como balões
        }
    } catch (error) {
        console.error('Erro ao inserir usuário:', error);
        alert('Ocorreu um erro ao tentar inserir o usuário.');
    }
});

function handleValidationError(errorData) {
    clearErrorMessages();

    if (errorData.errors && Array.isArray(errorData.errors)) {
        errorData.errors.forEach(error => {
            if (error.fieldName) {
                const fieldName = error.fieldName.toLowerCase();
                const errorTooltip = document.getElementById(`error-${fieldName}`);
                if (errorTooltip) {
                    errorTooltip.textContent = error.message;
                    errorTooltip.style.display = 'block';
                }
            } else {
                console.warn("Erro não possui campo 'fieldName':", error);
            }
        });
    } else {
        console.error('Formato de erro não reconhecido:', errorData);
    }
}

function clearErrorMessages() {
    const errorTooltips = document.querySelectorAll('.error-tooltip');
    errorTooltips.forEach(tooltip => {
        tooltip.textContent = '';
        tooltip.style.display = 'none';
    });
}