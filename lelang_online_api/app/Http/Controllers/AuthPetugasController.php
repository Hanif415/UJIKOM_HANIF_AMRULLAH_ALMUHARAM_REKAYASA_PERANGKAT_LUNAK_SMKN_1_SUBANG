<?php

namespace App\Http\Controllers;

use App\Petugas;
use Illuminate\Http\Request;

class AuthPetugasController extends Controller
{
    public function register(Request $request)
    {
        $nama_petugas = $request->input('nama_petugas');
        $username = $request->input('username');
        $password = $request->input('password');
        $level = $request->input('level');

        $petugas = new Petugas([
            'nama_petugas' => $nama_petugas,
            'username' => $username,
            'password' => $password,
            'level' => $level
        ]);

        if ($petugas->save()) {
            // $masyarakat->view_masyarakat = [
            //     'href' => 'masyarakat/register' . $masyarakat->id_user,
            //     'method' => 'GET'
            // ];

            $message = [
                'msg' => 'Selamat Datang',
                'masyarakat' => $petugas
            ];

            return response()->json($message, 201);
        }

        // $response = [
        //     'msg' => 'Error During Created',
        // ];

        // return response()->json($response, 404);
    }

    public function signin()
    {
        //
    }

    public function update(Request $request, $id)
    {
        $nama_petugas = $request->input('nama_petugas');
        $username = $request->input('username');
        $password = $request->input('password');
        $level = $request->input('level');

        $petugas = Petugas::where('id_petugas', $id)->firstOrFail();

        $petugas->nama_petugas = $nama_petugas;
        $petugas->username = $username;
        $petugas->password = $password;
        $petugas->level = $level;

        $petugas->update();

        if (!$petugas->update()) {
            return response()->json([
                'msg' => 'Error during update'
            ], 404);
        }

        $petugas->view_masyarakat = [
            'href' => 'api/masyarakat/update/' . $petugas->id_petugas,
            'method' => 'POST'
        ];

        $response = [
            'msg' => 'Petugas Updated',
            'meeting' => $request
        ];

        return response()->json($response, 200);
    }
}
