<?php

namespace App\Http\Controllers;

use App\Masyarakat;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AuthMasyakatController extends Controller
{
    public function register(Request $request)
    {
        $nama_lengkap = $request->input('nama_lengkap');
        $username = $request->input('username');
        $password = $request->input('password');
        $telp = $request->input('telp');

        $masyarakat = new Masyarakat([
            'nama_lengkap' => $nama_lengkap,
            'username' => $username,
            'password' => $password,
            'telp' => $telp
        ]);

        if ($masyarakat->save()) {
            $masyarakat->view_masyarakat = [
                'href' => 'masyarakat/register' . $masyarakat->id_user,
                'method' => 'GET'
            ];

            $message = [
                'msg' => 'Selamat Datang',
                'masyarakat' => $masyarakat
            ];

            return response()->json($message, 201);
        }

        $response = [
            'msg' => 'Error During Created',
        ];

        return response()->json($response, 404);
    }

    public function signin(Request $request)
    {
        $username = $request->input('username');
        $password = $request->input('password');

        if (Masyarakat::where('password', $password)->firstOrFail() && Masyarakat::where('username', $username)->firstOrFail()) {
            return response()->json([
                'value' => '1',
                'message' => 'Login Sucessfull'
            ], 200);
        }

        // if (Auth::attempt($credentials)) {
        //     return response()->json([
        //         'value' => '1',
        //         'message' => 'Login Sucessfull'
        //     ], 200);
        // } else {
        //     return response()->json([
        //         'value' => '0',
        //         'message' => 'Login Failed'
        //     ], 201);
        // }
    }

    public function update(Request $request, $id)
    {
        $nama_lengkap = $request->input('nama_lengkap');
        $username = $request->input('username');
        $password = $request->input('password');
        $telp = $request->input('telp');

        $masyarakat = Masyarakat::where('id_masyarakat', $id)->firstOrFail();

        $masyarakat->nama_lengkap = $nama_lengkap;
        $masyarakat->username = $username;
        $masyarakat->password = $password;
        $masyarakat->telp = $telp;

        $masyarakat->update();

        if (!$masyarakat->update()) {
            return response()->json([
                'msg' => 'Error during update'
            ], 404);
        }

        $masyarakat->view_masyarakat = [
            'href' => 'api/masyarakat/update/' . $masyarakat->id_user,
            'method' => 'POST'
        ];

        $response = [
            'msg' => 'Masyarakat Updated',
            'meeting' => $request
        ];

        return response()->json($response, 200);
    }
}
