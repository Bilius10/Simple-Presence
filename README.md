POST /api/classes
Autorização: ROLE_TEACHER
Request JSON:
{
"courseId": 5,
"title": "Aula de Java - 2026-03-29",
"startTime": "2026-03-29T10:00:00Z",
"durationMinutes": 60
}
Response 201:
{
"classId": 77,
"courseId": 5,
"title": "Aula de Java - 2026-03-29",
"startTime": "2026-03-29T10:00:00Z"
}

POST /api/classes/{classId}/sessions
Autorização: ROLE_TEACHER
Descrição: Gera sessão da aula e token/QR para validação de presença.
Request JSON:
{
"sessionName": "Sessão 1",
"expiresAt": "2026-03-29T11:00:00Z" // opcional
}
Response 201:
{
"sessionId": 555,
"classId": 77,
"sessionName": "Sessão 1",
"expiresAt": "2026-03-29T11:00:00Z",
"qrToken": "random-session-token",
"qrImageBase64": "data:image/png;base64,iVBORw0KGgoAAAANS..." // opcional
}

GET /api/sessions/{sessionId}/qrcode
Autorização: ROLE_TEACHER
Response 200 (application/json):
{
"sessionId": 555,
"qrToken": "random-session-token",
"qrImageBase64": "data:image/png;base64,iVBORw0KGgoAAAANS..."
}
Alternativa: responder como image/png com o conteúdo do QR.

POST /api/attendance/validate
Autorização: ROLE_STUDENT (token JWT do aluno)
Descrição: Aluno envia o token lido do QR para marcar presença.
Request JSON:
{
"qrToken": "random-session-token",
"latitude": -23.55052,      // opcional
"longitude": -46.633308,    // opcional
"deviceInfo": "Android 13"   // opcional
}
Response 200:
{
"attendanceId": 9999,
"studentId": 123,
"sessionId": 555,
"status": "PRESENT",
"timestamp": "2026-03-29T10:05:23Z"
}
Erros possíveis: 400 (token inválido), 410 (sessão expirada), 409 (já registrado)

GET /api/classes/{classId}/sessions/{sessionId}/attendances
Autorização: ROLE_TEACHER
Descrição: Lista presenças da sessão (professor).
Response 200:
[
{
"attendanceId": 9999,
"studentId": 123,
"studentName": "Aluno 1",
"status": "PRESENT",
"timestamp": "2026-03-29T10:05:23Z"
},
{
"attendanceId": 10000,
"studentId": 124,
"studentName": "Aluno 2",
"status": "ABSENT",
"timestamp": null
}
]

GET /api/me/attendances
Autorização: ROLE_STUDENT
Descrição: Aluno consulta suas presenças.
Query params opcionais: classId, from, to, page, size
Response 200:
[
{
"attendanceId": 9999,
"sessionId": 555,
"classId": 77,
"sessionName": "Sessão 1",
"status": "PRESENT",
"timestamp": "2026-03-29T10:05:23Z"
}
]

GET /api/classes/{classId}/attendance-summary
Autorização: ROLE_TEACHER
Descrição: Resumo percentual por aluno para a turma.
Response 200:
[
{
"studentId": 123,
"studentName": "Aluno 1",
"presentCount": 8,
"totalSessions": 10,
"attendanceRate": 80.0
}
]
