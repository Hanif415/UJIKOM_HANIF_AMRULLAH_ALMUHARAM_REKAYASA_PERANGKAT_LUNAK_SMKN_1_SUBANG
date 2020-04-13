<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\Masyarakat;
use App\Barang;
use App\History;

class Lelang extends Model
{
    protected $guarded = ['id_lelang'];
    protected $primaryKey = 'id_lelang';

    public function masyarakat()
    {
        $this->belongsTo(Masyarakat::class);
    }

    public function barang()
    {
        return $this->belongsTo(Barang::class);
    }

    public function history()
    {
        return $this->hasOne(History::class);
    }
}
