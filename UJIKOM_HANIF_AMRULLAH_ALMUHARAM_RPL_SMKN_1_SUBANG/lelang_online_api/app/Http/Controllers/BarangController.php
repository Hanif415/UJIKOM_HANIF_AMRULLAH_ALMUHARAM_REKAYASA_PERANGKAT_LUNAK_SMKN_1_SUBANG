<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Barang;

class BarangController extends Controller
{
    public function index()
    {
        $barang = Barang::all();
        foreach ($barang as $barangs) {
            $barangs->view_metting = [
                'href' => 'api/barang' . $barangs->id_barang,
                'method' => 'GET'
            ];
        }

        $response = [
            'msg' => 'List of All Meetings',
            'meetings' => $barang
        ];

        return response()->json($response, 200);
    }

    public function store(Request $request)
    {
        $nama_barang = $request->input('nama_barang');
        $kategori_barang = $request->input('kategori_barang');
        $tgl = $request->input('tgl');
        $harga_awal = $request->input('harga_awal');
        $deskripsi_barang = $request->input('deskripsi_barang');

        $barang = new Barang([
            'nama_barang' => $nama_barang,
            'kategori_barang' => $kategori_barang,
            'tgl' => $tgl,
            'harga_awal' => $harga_awal,
            'deskripsi_barang' => $deskripsi_barang
        ]);

        if ($barang->save()) {
            $barang->view_barang = [
                'href' => 'api/barang' . $barang->id,
                'method' => 'GET'
            ];

            $message = [
                'msg' => 'Barang Ditambahkan',
                'meeting' => $barang
            ];

            return response()->json($message, 201);
        }

        $response = [
            'msg' => 'Error During Created',
        ];

        return response()->json($response, 404);
    }

    public function show($id)
    {
        $barang = Barang::where('id_barang', $id)->firstOrFail();

        $barang->view_barang = [
            'href' => 'api/barang',
            'method' => 'GET'
        ];

        $response = [
            'msg' => 'Meeting Information',
            'barang' => $barang
        ];

        return response()->json($response, 200);
    }

    public function update(Request $request, $id)
    {
        // $this->validate($request, [
        //     'title' => 'required',
        //     'description' => 'required',
        //     'time' => 'required',
        //     'user_id' => 'required'  `
        // ]);

        $nama_barang = $request->input('nama_barang');
        $kategori_barang = $request->input('kategori_barang');
        $tgl = $request->input('tgl');
        $harga_awal = $request->input('harga_awal');
        $deskripsi_barang = $request->input('deskripsi_barang');

        $barang = Barang::where('id_barang', $id)->firstOrFail();

        // if (!$barang->users()->where('users.id', $petugas_id)->first()) {
        //     return response()->json([
        //         'msg' => 'User not registered for meeting, update not sucssesful'
        //     ], 404);
        // };

        $barang->nama_barang = $nama_barang;
        $barang->kategori_barang = $kategori_barang;
        $barang->tgl = $tgl;
        $barang->harga_awal = $harga_awal;
        $barang->deskripsi_barang = $deskripsi_barang;

        $barang->update();

        if (!$barang->update()) {
            return response()->json([
                'msg' => 'Error during update'
            ], 404);
        }

        $barang->view_barang = [
            'href' => 'api/v1/meeting/' . $barang->id_barang,
            'method' => 'GET'
        ];

        $response = [
            'msg' => 'Meeting Updated',
            'meeting' => $request
        ];

        return response()->json($response, 200);
    }

    public function destroy($id)
    {
        $barang = Barang::where('id_barang', $id);

        $barang->delete();

        // $barang = $barang->users;
        // $barang->users()->detach();

        // if (!$meeting->delete()) {
        //     foreach ($users as $user) {
        //         $meeting->users()->attach($user);
        //     }
        //     return response()->json([
        //         'msg' => 'Deletion Failed'
        //     ], 404);
        // }

        $response = [
            'msg' => 'Meeting Deleted',
            'create' => [
                'href' => 'api/v1/meeting',
                'method' => 'POST',
                'params' => 'title, description, time'
            ]
        ];

        return response()->json($response, 200);
    }
}
