<?php

namespace App\Http\Controllers;

use App\Masyarakat;
use App\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Str;

class AuthController extends Controller
{
    public function store(Request $request)
    {
        $user = User::create([
            'username' => $request->username,
            'password' => bcrypt($request->password),
            'id_level' => $request->id_level,
            'remember_token' => Str::random(7),
        ]);

        if ($request->id_level == 1 || $request->id_level == 2) {
            $petugas = $user->petugas()->create([
                'id_user' => $user->id_user,
                'nama_petugas' => $request->nama_petugas
            ]);
            $success['nama_petugas'] = $petugas->nama_petugas;
        } else if ($request->id_level == 3) {
            $masyarakat = $user->masyarakat()->create([
                'id_user' => $user->id_user,
                'nama_lengkap' => $request->nama_lengkap,
                'telp' => $request->telp
            ]);
            $success['nama_lengkap'] = $masyarakat->nama_lengkap;
        }

        $success['level'] = $user->id_level;
        $success['remember_token'] = $user->remember_token;

        return response()->json([
            'message' => 'Data Created',
            'user' => $success
        ]);
    }

    public function signin(Request $request)
    {
        $credentials = [
            'username' => $request->username,
            'password' => $request->password,
        ];

        if (Auth::attempt($credentials)) {

            $id_level = Auth::user()->id_level;
            $id_user = Auth::user()->id_user;
            $token = Auth::user()->remember_token;

            if ($id_level == 1 || $id_level == 2) {
                $petugas = User::find($id_user)->petugas;
                $data_user['data'] = $petugas;
            } elseif ($id_level == 3) {
                $masyarakat = User::find($id_user)->masyarakat;
                $data_user['data'] = $masyarakat;
            }

            return response()->json([
                'value' => '1',
                'message' => 'Login Successfull',
                'id_level' => $id_level,
                'id_user' => $id_user,
                'token' => $token,
                // 'user' => $data_user,
                'value' => '1',
            ], 200);
        } else {
            return response()->json([
                'value' => '0',
                'message' => 'Wrong Username or Password'
            ], 201);
        }
    }
}
