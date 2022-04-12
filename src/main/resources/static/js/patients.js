window.onload = (e) => {
    e.preventDefault();
    getPatients();

}

async function getPatients() {
    const request = await fetch('/api/patient/', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })

    const patients = await request.json();
    let html = '';
    patients.forEach(patient => {
        html += `
            <tr>
                <td>${patient.id}</td>
                <td>${patient.lastName},  ${patient.name}</td>
                <td>${patient.dni}</td>
                <td>${patient.address.street} ${patient.address.number}, ${patient.address.location}, ${patient.address.province}</td>
                <td>
                  <button type="button" class="btn btn-primary" ><span class="material-icons">edit</span></button>
                    <button type="button" class="btn btn-danger" onclick="deletePatient(${patient.id})"><span class="material-icons">delete</span></button>
                </td>
            </tr>
        `;
    });

    document.querySelector('#patients tbody').innerHTML = html;

}

async function addPatients() {
    const patient = {
        lastName: document.querySelector('#recipient-lastName').value,
        name: document.querySelector('#recipient-name').value,
        dni: document.querySelector('#recipient-dni').value,
        address: {
            street: document.querySelector('#recipient-street').value,
            number: document.querySelector('#recipient-number').value,
            location: document.querySelector('#recipient-location').value,
            province: document.querySelector('#recipient-province').value
        }
    }
    if (patient.name === '' || patient.lastName === '' || patient.dni === '' || patient.address.street === '' || patient.address.number === '' || patient.address.location === '' || patient.address.province === '') {
        alert('Por favor complete todos los campos');
    } else {
        const request = await fetch('/api/patient/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(patient)
        })
        if (request.status === 200) {
            alert('Paciente agregado');
            await getPatients();
        }
    }
}

async function deletePatient(id) {
    if (!confirm('¿Está seguro de eliminar el paciente?')) {
        return;
    }
    await fetch(`/api/patient/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
    location.reload();
}