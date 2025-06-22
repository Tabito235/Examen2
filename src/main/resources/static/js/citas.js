let tabla;

$(document).ready(function () {
    cargarCitas();

    $('#formCita').submit(function (e) {
        e.preventDefault();
        guardarCita();
    });
});

function cargarCitas() {
    tabla = $('#tablaCitas').DataTable({
        ajax: {
            url: "/api/citas",
            dataSrc: ''
        },
        destroy: true,
        columns: [
            { data: "id" },
            { data: "idPaciente" },
            { data: "fecha" },
            { data: "motivo" },
            {
                data: null,
                render: function (data) {
                    return `
                        <button class="btn btn-warning btn-sm" onclick='editarCita(${JSON.stringify(data)})'>Editar</button>
                        <button class="btn btn-danger btn-sm" onclick='eliminarCita(${data.id})'>Eliminar</button>
                    `;
                }
            }
        ]
    });
}

function abrirModalNueva() {
    $('#formCita')[0].reset();
    $('#id').val('');
    $('#modalCita').modal('show');
}

function editarCita(cita) {
    $('#id').val(cita.id);
    $('#idPaciente').val(cita.idPaciente);
    $('#fecha').val(cita.fecha.substring(0, 16));
    $('#motivo').val(cita.motivo);
    $('#modalCita').modal('show');
}

function guardarCita() {
    const cita = {
        idPaciente: parseInt($('#idPaciente').val()),
        fecha: $('#fecha').val(),
        motivo: $('#motivo').val()
    };
    const id = $('#id').val();

    if (isNaN(cita.idPaciente) || cita.idPaciente <= 0) return alert('Id Paciente inválido');
    if (!cita.fecha) return alert('Fecha requerida');
    if (!cita.motivo || cita.motivo.length < 3) return alert('Motivo muy corto');

    const url = id ? `/api/citas/${id}` : '/api/citas';

    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(cita),
        success: () => {
            $('#modalCita').modal('hide');
            tabla.ajax.reload();
        }
    });
}

function eliminarCita(id) {
    if (!confirm('¿Estás seguro de eliminar esta cita?')) return;
    $.ajax({
        url: `/api/citas/${id}`,
        type: 'DELETE',
        success: () => tabla.ajax.reload()
    });
}
